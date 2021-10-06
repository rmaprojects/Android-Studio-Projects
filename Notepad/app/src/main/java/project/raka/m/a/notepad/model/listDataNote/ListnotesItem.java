package project.raka.m.a.notepad.model.listDataNote;

import com.google.gson.annotations.SerializedName;

public class ListnotesItem{

	@SerializedName("id_notes")
	private String idNotes;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("judul")
	private String judul;

	@SerializedName("category")
	private String category;

	@SerializedName("content")
	private String content;

	@SerializedName("username")
	private String username;

	public String getIdNotes(){
		return idNotes;
	}

	public String getTanggal(){
		return tanggal;
	}

	public String getIdUser(){
		return idUser;
	}

	public String getJudul(){
		return judul;
	}

	public String getCategory(){
		return category;
	}

	public String getContent(){
		return content;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"ListnotesItem{" + 
			"id_notes = '" + idNotes + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",judul = '" + judul + '\'' + 
			",category = '" + category + '\'' + 
			",content = '" + content + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}