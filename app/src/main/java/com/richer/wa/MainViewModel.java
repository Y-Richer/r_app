package com.richer.wa;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.richer.wa.base.BaseObserver;
import com.richer.wa.base.BaseViewModel;
import com.richer.wa.home.model.HotSearchModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(API api) {
        super(api);
    }

    private MutableLiveData<HotSearchModel> hotSearchKeys = new MutableLiveData<>();

    public LiveData<HotSearchModel> hotSearchKeys() {
        return hotSearchKeys;
    }


    /**
     * 获取搜索热词
     */
    public void getHotSearchKey() {
        api.getHotSearchKey()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HotSearchModel>() {
                    @Override
                    protected void onSuccess(HotSearchModel hotSearchModel) {
                        hotSearchKeys.setValue(hotSearchModel);
                    }

                    @Override
                    protected void onFail(int errorCode, String errorMsg) {
                        Log.d("TAG", "onFail: ");
                    }
                });
    }

}
