package com.raka.m.a.portalberitasmk.network;

import com.raka.m.a.portalberitasmk.model.RequestGetPortalBeritaSMK;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServices {

    @GET("module/informasiSMK.php")
    Call<RequestGetPortalBeritaSMK> RequestPortalBeritaSMK();
}
