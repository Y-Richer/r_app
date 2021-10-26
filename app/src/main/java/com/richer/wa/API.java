package com.richer.wa;

import com.richer.wa.home.model.HotSearchModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

/**
 * create by richer on 2021/10/14
 * API父类
 * 所有API继承此类
 */
public interface API {


    @GET("/hotkey/json")
    Observable<HotSearchModel> getHotSearchKey();

}
