package com.richer.wa.network;

import java.lang.reflect.Field;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtil {

    public static String baseUrl = "https://www.wanandroid.com";

    private static volatile Retrofit sRetrofit;

    private static OkHttpClient sClient;

    private static ConcurrentHashMap<Class, Object> apiMap = new ConcurrentHashMap<>();

    private static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            synchronized (NetUtil.class) {
                if (sRetrofit == null) {
                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .client(getClient())
                            .build();
                }
            }
        }
        return sRetrofit;
    }

    public static <T> T getAPI(Class<T> apiClass) {
        if (apiMap.contains(apiClass)) {
            return (T) apiMap.get(apiClass);
        }
        T object = getRetrofit().create(apiClass);
        apiMap.put(apiClass, object);
        return object;
    }

    public static HomeAPI getHomeApi() {
        return getAPI(HomeAPI.class);
    }

    private static OkHttpClient getClient() {
        if (sClient == null) {
            synchronized (NetUtil.class) {
                if (sClient == null) {
                    sClient = handlerSSLHandShake();
                }
            }
        }
        return sClient;
    }

    /**
     * Retrofit2出现错误
     * java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
     * 需要忽略证书验证
     * @return
     */
    public static OkHttpClient handlerSSLHandShake() {
        OkHttpClient client = new OkHttpClient();
        TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustManagers, new SecureRandom());

            HostnameVerifier verifier = (hostname, session) -> true;

            String workerClassName = "okhttp3.OkHttpClient";
            Class workerClass = Class.forName(workerClassName);
            Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
            hostnameVerifier.setAccessible(true);
            hostnameVerifier.set(client, verifier);

            Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactoryOrNull");
            sslSocketFactory.setAccessible(true);
            sslSocketFactory.set(client, sc.getSocketFactory());
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (KeyManagementException keyManagementException) {
            keyManagementException.printStackTrace();
        }
        return client;
    }

}
