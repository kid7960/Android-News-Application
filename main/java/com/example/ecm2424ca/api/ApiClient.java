package com.example.ecm2424ca.api;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://newsapi.org/v2/";
    private static ApiClient apiClient;
    public static Retrofit retrofit;

    private ApiClient(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiClient getInstance(){
        if (apiClient ==null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public ApiInterface getApi() {
        return retrofit.create(ApiInterface.class);
    }
}
