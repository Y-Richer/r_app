package com.richer.wa.home.model;

import com.google.gson.annotations.SerializedName;
import com.richer.wa.base.BaseResponseModel;

import java.util.List;

public class ArticleListResponseModel extends BaseResponseModel {

    private ArticleListData data;

    public ArticleListData getData() {
        return data;
    }

    public void setData(ArticleListData data) {
        this.data = data;
    }

    public static class ArticleListData {
        @SerializedName("curPage")
        private int curPage;

        @SerializedName("offset")
        private int offset;

        @SerializedName("over")
        private boolean over;

        @SerializedName("pageCount")
        private int pageCount;

        @SerializedName("size")
        private int size;

        @SerializedName("total")
        private int total;

        @SerializedName("datas")
        private List<ArticleInfo> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ArticleInfo> getDatas() {
            return datas;
        }

        public void setDatas(List<ArticleInfo> datas) {
            this.datas = datas;
        }
    }



}
