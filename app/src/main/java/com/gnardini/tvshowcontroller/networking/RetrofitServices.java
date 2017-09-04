package com.gnardini.tvshowcontroller.networking;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {

    private final Map<Class, Object> services;
    private final HostManager hostManager;

    public RetrofitServices(HostManager hostManager) {
        this.services = new HashMap<>();
        this.hostManager = hostManager;
    }

    public <ServiceType> ServiceType createService(Class<ServiceType> clazz) {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(hostManager.getHost())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }
}
