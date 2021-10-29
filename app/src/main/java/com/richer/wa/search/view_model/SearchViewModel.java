package com.richer.wa.search.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.richer.wa.API;
import com.richer.wa.base.BaseObserver;
import com.richer.wa.base.BaseViewModel;
import com.richer.wa.home.model.ArticleListResponseModel;
import com.richer.wa.search.model.HotSearchModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchViewModel extends BaseViewModel {

    public SearchViewModel(API api) {
        super(api);
    }

    int mSearchPage = 0;

    private MutableLiveData<HotSearchModel> _hotSearchModel = new MutableLiveData<>();

    public LiveData<HotSearchModel> hotSearchModel() {
        return _hotSearchModel;
    }

    private MutableLiveData<ArticleListResponseModel> _searchArticles = new MutableLiveData<>();

    public LiveData<ArticleListResponseModel> searchArticles() {
        return _searchArticles;
    }

    public void getHotSearchWord() {
        api.getHotSearchKey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HotSearchModel>() {
                    @Override
                    protected void onSuccess(HotSearchModel hotSearchModel) {
                        _hotSearchModel.setValue(hotSearchModel);
                    }

                    @Override
                    protected void onFail(int errorCode, String errorMsg) {

                    }
                });
    }

    public void doSearchArticle(boolean isSearch, String key) {
        if (isSearch) {
            mSearchPage = 1;
        } else {
            mSearchPage++;
        }
        api.searchArticle(mSearchPage, key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ArticleListResponseModel>() {
                    @Override
                    protected void onSuccess(ArticleListResponseModel articleListResponseModel) {
                        _searchArticles.setValue(articleListResponseModel);
                    }

                    @Override
                    protected void onFail(int errorCode, String errorMsg) {

                    }
                });
    }

}
