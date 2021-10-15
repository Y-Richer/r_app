package com.richer.wa.base;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * create by richer on 2021/10/13
 *
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        if (t instanceof BaseResponseModel) {
            BaseResponseModel result = (BaseResponseModel) t;
            int code = result.getErrorCode();
            if (code == 0) {
                onSuccess(t);
            } else {
                onFail(code, result.getErrorMsg());
            }
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFail(-1, e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T t);

    protected abstract void onFail(int errorCode, String errorMsg);
}
