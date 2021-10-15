package com.richer.wa.home.model;

import com.google.gson.annotations.SerializedName;
import com.richer.wa.base.BaseResponseModel;

import java.util.List;

/**
 * create by richer on 2021/10/15
 */
public class TopArticleListModel extends BaseResponseModel {

    @SerializedName("data")
    List<ArticleInfo> topArticles;

    public List<ArticleInfo> getTopArticles() {
        return topArticles;
    }

    public void setTopArticles(List<ArticleInfo> topArticles) {
        this.topArticles = topArticles;
    }
}
