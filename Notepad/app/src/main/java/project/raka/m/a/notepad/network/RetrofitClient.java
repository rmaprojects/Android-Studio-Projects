package project.raka.m.a.notepad.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Berfungsi sebagai penghubung komunikasi
public class RetrofitClient {
    public static final String BASE_URL = "http://192.168.3.3/notepads/";
    //BASE_URL g boleh pakek https, karena ini localhost
    public static Retrofit setInit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static APIServices getInstance() {
        return setInit().create(APIServices.class);
    }
}

