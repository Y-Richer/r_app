package com.richer.wa;

import android.app.Application;
import android.content.Context;

import com.richer.wa.network.NetUtil;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
        NetUtil.handlerSSLHandShake();
    }

}
