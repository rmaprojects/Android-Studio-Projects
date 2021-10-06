package com.raka.m.a.portalberitasmk.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raka.m.a.portalberitasmk.R;
import com.raka.m.a.portalberitasmk.adapter.AdapterListBeritaSMK;
import com.raka.m.a.portalberitasmk.model.InformasiSmkItem;
import com.raka.m.a.portalberitasmk.model.RequestGetPortalBeritaSMK;
import com.raka.m.a.portalberitasmk.network.APIServices;
import com.raka.m.a.portalberitasmk.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class informasiSMKActivity extends AppCompatActivity {

    RecyclerView listberitaSMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portalsmk);
        getSupportActionBar().setTitle("Portal Berita SMK");

        listberitaSMK = (RecyclerView)findViewById(R.id.ListpoprtalberitaSMK);
        listberitaSMK.setHasFixedSize(true);
        listberitaSMK.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        tampilkanberitaSMK();

    }

    private void tampilkanberitaSMK() {

        APIServices apiServices = RetrofitClient.getInstance();
        Call<RequestGetPortalBeritaSMK> requestGetPortalBeritaSMK = apiServices.RequestPortalBeritaSMK();

        requestGetPortalBeritaSMK.enqueue(new Callback<RequestGetPortalBeritaSMK>() {
            @Override
            public void onResponse(Call<RequestGetPortalBeritaSMK> call, Response<RequestGetPortalBeritaSMK> response) {
                if (response.isSuccessful()){
                    Log.d("MANTAP", "Pengambilan seikai da!");
                    List<InformasiSmkItem> itemisiberitaSMK = response.body().getInformasiSmk();
                    boolean status = response.body().isStatus();
                    if (status == true) {
                        AdapterListBeritaSMK adapterListBeritaSMK = new AdapterListBeritaSMK(informasiSMKActivity.this, itemisiberitaSMK);
                        listberitaSMK.setAdapter(adapterListBeritaSMK);
                    } else {
                        Toast.makeText(informasiSMKActivity.this, "ERROR" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RequestGetPortalBeritaSMK> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }
}
