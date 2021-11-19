package com.richer.wa.classify.view_model;

import androidx.lifecycle.MutableLiveData;

import com.richer.wa.API;
import com.richer.wa.base.BaseObserver;
import com.richer.wa.base.BaseViewModel;
import com.richer.wa.classify.model.ClassifyModel;
import com.richer.wa.utils.RToast;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ClassifyViewModel extends BaseViewModel {
    public ClassifyViewModel(API api) {
        super(api);
    }

    public MutableLiveData<ClassifyModel> classifyArticleLiveData = new MutableLiveData<>();

    public void getClassifyArticle() {
        api.getClassifyArticle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ClassifyModel>() {
                    @Override
                    protected void onSuccess(ClassifyModel classifyModel) {
                        if (classifyModel != null) {
                            classifyArticleLiveData.setValue(classifyModel);
                        } else {
                            RToast.show("no data");
                        }
                    }

                    @Override
                    protected void onFail(int errorCode, String errorMsg) {
                        RToast.show("error: "+errorMsg);
                    }
                });
    }

}
