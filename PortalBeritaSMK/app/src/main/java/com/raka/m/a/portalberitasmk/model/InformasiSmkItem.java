package com.raka.m.a.portalberitasmk.model;

import com.google.gson.annotations.SerializedName;

public class InformasiSmkItem{

	@SerializedName("picture_berita")
	private String pictureBerita;

	@SerializedName("headline_berita")
	private String headlineBerita;

	@SerializedName("nama_penulis")
	private String namaPenulis;

	@SerializedName("judul_berita")
	private String judulBerita;

	@SerializedName("isi_berita")
	private String isiBerita;

	@SerializedName("tanggal_posting")
	private String tanggalPosting;

	public String getPictureBerita(){
		return pictureBerita;
	}

	public String getHeadlineBerita(){
		return headlineBerita;
	}

	public String getNamaPenulis(){
		return namaPenulis;
	}

	public String getJudulBerita(){
		return judulBerita;
	}

	public String getIsiBerita(){
		return isiBerita;
	}

	public String getTanggalPosting(){
		return tanggalPosting;
	}
}