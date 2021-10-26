package com.richer.wa.home.model;

import com.google.gson.annotations.SerializedName;
import com.richer.wa.base.BaseResponseModel;

import java.util.List;

public class HotSearchModel extends BaseResponseModel {

    @SerializedName("data")
    private List<HotSearchBean> data;

    public List<HotSearchBean> getData() {
        return data;
    }

    public void setData(List<HotSearchBean> data) {
        this.data = data;
    }

    public static class HotSearchBean {
        @SerializedName("id")
        int id;

        @SerializedName("link")
        String link;

        @SerializedName("name")
        String name;

        @SerializedName("order")
        int order;

        @SerializedName("visible")
        int visible;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }
    }

}
