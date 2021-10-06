package com.raka.m.a.portalberitasmk.network;

import com.raka.m.a.portalberitasmk.url.URLCollections;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit setInit(){
        return new Retrofit.Builder()
                .baseUrl(URLCollections.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIServices getInstance(){
        return setInit().create(APIServices.class);
    }
}
