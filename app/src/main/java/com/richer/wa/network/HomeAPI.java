package com.richer.wa.network;

import com.richer.wa.API;
import com.richer.wa.home.model.ArticleListResponseModel;
import com.richer.wa.home.model.TopArticleListModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeAPI extends API {

    @GET("/article/list/{page}/json")
    Observable<ArticleListResponseModel> getArticleList(@Path("page") int page);

    @GET("/article/top/json")
    Observable<TopArticleListModel> getTopArticleList();

}
