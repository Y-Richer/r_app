package com.richer.wa.home.view_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.richer.wa.API;
import com.richer.wa.base.BaseObserver;
import com.richer.wa.base.BaseViewModel;
import com.richer.wa.home.model.ArticleListResponseModel;
import com.richer.wa.home.model.TopArticleListModel;
import com.richer.wa.network.HomeAPI;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    private HomeAPI mApi = (HomeAPI) api;

    private int page;

    public HomeViewModel(API api) {
        super(api);
        if (api instanceof HomeAPI) {
            mApi = (HomeAPI) api;
        }
    }

    /**
     * 检查api
     *
     * @return
     */
    private boolean checkApi() {
        return api instanceof HomeAPI;
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

    private MutableLiveData<TopArticleListModel> topArticles = new MutableLiveData<>();

    public LiveData<TopArticleListModel> addTopArticles() {
        return topArticles;
    }

    /**
     * 获取文章列表
     *
     * @param isRefresh
     */
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
                    .subscribe(new BaseObserver<ArticleListResponseModel>() {
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

    /**
     * 获取置顶文章
     */
    public void getTopArticleList() {
        if (checkApi()) {
            mApi.getTopArticleList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<TopArticleListModel>() {
                        @Override
                        protected void onSuccess(TopArticleListModel topArticleListModel) {
                            refreshFinish.setValue(true);
                            topArticles.setValue(topArticleListModel);
                        }

                        @Override
                        protected void onFail(int errorCode, String errorMsg) {
                            Log.d("TAG", "onFail: ");
                        }
                    });
        }
    }

}
