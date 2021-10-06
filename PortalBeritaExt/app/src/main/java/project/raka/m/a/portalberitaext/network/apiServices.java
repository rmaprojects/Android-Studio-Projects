package project.raka.m.a.portalberitaext.network;

import project.raka.m.a.portalberitaext.model.ResponseBerita;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiServices {
    @GET("/v2/top-headlines")
    Call<ResponseBerita> getListNews(
            @Query("country") String country,
            @Query("q") String keyword,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
}
