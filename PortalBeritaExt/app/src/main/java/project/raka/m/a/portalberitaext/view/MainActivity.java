package project.raka.m.a.portalberitaext.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import project.raka.m.a.portalberitaext.R;
import project.raka.m.a.portalberitaext.adapter.AdapterBerita;
import project.raka.m.a.portalberitaext.model.ArticlesItem;
import project.raka.m.a.portalberitaext.model.ResponseBerita;
import project.raka.m.a.portalberitaext.network.RetrofitClient;
import project.raka.m.a.portalberitaext.network.apiServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvListBerita;
    TextView txInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        apiServices apiServices = RetrofitClient.getInstance();
        Call<ResponseBerita> beritaCall = apiServices.getListNews(country, q, category, apiKey);

        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()) {

                    int status = response.body().getTotalResults();
                    List<ArticlesItem> itemListBerita = response.body().getArticles();
                    Log.d("LOG", "Article " + response.body().getArticles());

                    if (status != 0) {
                        AdapterBerita adapterListBerita = new AdapterBerita(MainActivity.this, itemListBerita);
                        rvListBerita.setAdapter(adapterListBerita);
                        Toast.makeText(MainActivity.this, "Data berita berhasil di tampilkan", Toast.LENGTH_SHORT).show();
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