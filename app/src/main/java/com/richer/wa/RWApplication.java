package com.richer.wa;

import android.app.Application;
import android.content.Context;

import com.richer.wa.network.NetWorkUtil;

public class RWApplication extends Application {

    private static Application sApplication;
    private static Context sContext;

    public static Application getInstance() {
        return sApplication;
    }

    public static Context getAppContext() {
        return sContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        sApplication = this;
        sContext = base;
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkUtil.handlerSSLHandShake();
    }

}
