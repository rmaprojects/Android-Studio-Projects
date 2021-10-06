package ei.eseptiyadi.apbexternal.network;

import ei.eseptiyadi.apbexternal.model.ResponseBerita;
import ei.eseptiyadi.apbexternal.url.UrlCollections;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("/v2/top-headlines")
    Call<ResponseBerita> getListNews(
            @Query("country") String country,
            @Query("q") String keyword,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );
}
