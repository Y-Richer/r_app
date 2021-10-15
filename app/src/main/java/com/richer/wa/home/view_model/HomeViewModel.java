package com.richer.wa.home.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.richer.wa.API;
import com.richer.wa.base.BaseObservable;
import com.richer.wa.base.BaseViewModel;
import com.richer.wa.home.model.ArticleListResponseModel;
import com.richer.wa.network.HomeAPI;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    private HomeAPI mApi;

    private int page;

    public HomeViewModel(API api) {
        super(api);
    }

    /**
     * 检查api
     * @return
     */
    private boolean checkApi() {
        if (api instanceof HomeAPI) {
            mApi = (HomeAPI) api;
            return true;
        }
        return false;
    }

    private MutableLiveData<Boolean> refreshFinish = new MutableLiveData<>();

    public LiveData<Boolean> refreshFinish() {
        return refreshFinish;
    }

    private MutableLiveData<Boolean> loadMoreFinish = new MutableLiveData<>();

    public LiveData<Boolean> loadMoreFinish() {
        return loadMoreFinish;
    }

    private MutableLiveData<ArticleListResponseModel> refreshArticles = new MutableLiveData<>();

    public LiveData<ArticleListResponseModel> getRefreshArticles() {
        return refreshArticles;
    }

    private MutableLiveData<ArticleListResponseModel> addArticles = new MutableLiveData<>();

    public LiveData<ArticleListResponseModel> addRefreshArticles() {
        return addArticles;
    }

    public void getArticleList(boolean isRefresh) {
        if (checkApi()) {
            //刷新page置0, 否则页数+1
            if (isRefresh) {
                page = 0;
            } else {
                page += 1;
            }
            mApi.getArticleList(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObservable<ArticleListResponseModel>() {
                        @Override
                        protected void onSuccess(ArticleListResponseModel articleListResponseModel) {
                            if (isRefresh) {
                                refreshFinish.setValue(true);
                                refreshArticles.setValue(articleListResponseModel);
                            } else {
                                loadMoreFinish.setValue(true);
                                addArticles.setValue(articleListResponseModel);
                            }
                        }

                        @Override
                        protected void onFail(int errorCode, String errorMsg) {

                        }
                    });
        }
    }

}
