package com.richer.wa;

import com.richer.wa.home.model.ArticleListResponseModel;
import com.richer.wa.search.model.HotSearchModel;
import com.richer.wa.home.model.TopArticleListModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * create by richer on 2021/10/14
 * API父类
 * 所有API继承此类
 */
public interface API {

    @GET("/hotkey/json")
    Observable<HotSearchModel> getHotSearchKey();

    @GET("/article/list/{page}/json")
    Observable<ArticleListResponseModel> getArticleList(@Path("page") int page);

    @GET("/article/top/json")
    Observable<TopArticleListModel> getTopArticleList();

}
