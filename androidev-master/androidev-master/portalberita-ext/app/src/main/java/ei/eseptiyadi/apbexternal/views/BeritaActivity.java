package ei.eseptiyadi.apbexternal.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import ei.eseptiyadi.apbexternal.R;
import ei.eseptiyadi.apbexternal.adapter.AdapterListBerita;
import ei.eseptiyadi.apbexternal.model.ArticlesItem;
import ei.eseptiyadi.apbexternal.model.ResponseBerita;
import ei.eseptiyadi.apbexternal.network.ApiServices;
import ei.eseptiyadi.apbexternal.network.RetrofitClient;
import ei.eseptiyadi.apbexternal.url.UrlCollections;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BeritaActivity extends AppCompatActivity {

    RecyclerView rvListBerita;
    TextView txInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        rvListBerita = (RecyclerView)findViewById(R.id.rvListBerita);
        txInformation = (TextView) findViewById(R.id.textInformation);
        rvListBerita.setHasFixedSize(true);
        rvListBerita.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        String apiKey = "2f88b5d16ed341b2a6b59f507dc331d0";
        String country = "id";
        String category = "health";
        String q = "covid-19";

        loadBerita(apiKey, country, category, q);
    }

    private void loadBerita(String apiKey, String country, String category, String q) {

        ApiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseBerita> beritaCall = apiServices.getListNews(country, q, category, apiKey);

        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()) {

                    int status = response.body().getTotalResults();
                    List<ArticlesItem> itemListBerita = response.body().getArticles();
                    Log.d("LOG", "Article " + response.body().getArticles());

                    if (status != 0) {
                        AdapterListBerita adapterListBerita = new AdapterListBerita(BeritaActivity.this, itemListBerita);
                        rvListBerita.setAdapter(adapterListBerita);
                        Toast.makeText(BeritaActivity.this, "Data berita berhasil di tampilkan", Toast.LENGTH_SHORT).show();
                    } else {
                        txInformation.setVisibility(View.VISIBLE);
                        txInformation.setText("Data berita total " + response.body().getTotalResults() + " silahkan cari dengan keyword lain");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {

            }
        });
    }
}