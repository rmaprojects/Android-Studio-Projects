package com.raka.m.a.portalberitasmk.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RequestGetPortalBeritaSMK{

	@SerializedName("informasi_smk")
	private List<InformasiSmkItem> informasiSmk;

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<InformasiSmkItem> getInformasiSmk(){
		return informasiSmk;
	}

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}