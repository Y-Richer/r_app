package com.richer.wa;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.richer.wa.base.BaseViewModel;

import java.lang.reflect.InvocationTargetException;

/**
 * create by richer on 2021/10/14
 * ViewModel工厂,带api参数
 */
public class RWViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private API api;

    public RWViewModelFactory(API api) {
        this.api = api;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (BaseViewModel.class.isAssignableFrom(modelClass)) {
            try {
                return modelClass.getConstructor(API.class).newInstance(api);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return super.create(modelClass);
    }
}
