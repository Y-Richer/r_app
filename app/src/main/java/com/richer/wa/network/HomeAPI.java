package com.richer.wa.network;

import com.richer.wa.home.model.ArticleListResponseModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeAPI {

    @GET("article/list/{page}/json")
    Observable<ArticleListResponseModel> getArticleList(@Path("page") int page);

}
