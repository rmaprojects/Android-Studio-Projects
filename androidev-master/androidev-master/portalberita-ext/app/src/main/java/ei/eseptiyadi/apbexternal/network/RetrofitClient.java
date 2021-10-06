package ei.eseptiyadi.apbexternal.network;

import ei.eseptiyadi.apbexternal.url.UrlCollections;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit setInit(){
        return new Retrofit.Builder()
                .baseUrl(UrlCollections.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiServices getInstance(){
        return setInit().create(ApiServices.class);
    }
}
