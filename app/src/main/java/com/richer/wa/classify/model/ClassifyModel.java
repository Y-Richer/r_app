package com.richer.wa.classify.model;

import com.richer.wa.base.BaseResponseModel;
import com.richer.wa.home.model.ArticleInfo;

import java.util.List;

public class ClassifyModel extends BaseResponseModel {

    List<ChapterBean> data;

    public List<ChapterBean> getData() {
        return data;
    }

    public void setData(List<ChapterBean> data) {
        this.data = data;
    }

    public static class ChapterBean {
        int cid;
        String name;
        List<ArticleInfo> articles;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ArticleInfo> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticleInfo> articles) {
            this.articles = articles;
        }
    }

}
