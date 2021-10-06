package project.raka.m.a.portalberitaext.network;

import project.raka.m.a.portalberitaext.url.UrlServices;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit setInit(){
        return new Retrofit.Builder()
                .baseUrl(UrlServices.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static apiServices getInstance(){
        return setInit().create(apiServices.class);
    }
}
