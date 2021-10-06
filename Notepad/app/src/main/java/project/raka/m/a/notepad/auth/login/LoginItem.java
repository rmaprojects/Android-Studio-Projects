package project.raka.m.a.notepad.auth.login;

import com.google.gson.annotations.SerializedName;

public class LoginItem{

	@SerializedName("password")
	private String password;

	@SerializedName("hash_useraccess")
	private String hashUseraccess;

	@SerializedName("level")
	private String level;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("datauser_created")
	private String datauserCreated;

	@SerializedName("username")
	private String username;

	public String getPassword(){
		return password;
	}

	public String getHashUseraccess(){
		return hashUseraccess;
	}

	public String getLevel(){
		return level;
	}

	public String getIdUser(){
		return idUser;
	}

	public String getDatauserCreated(){
		return datauserCreated;
	}

	public String getUsername(){
		return username;
	}
}