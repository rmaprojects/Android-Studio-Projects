package project.raka.m.a.notepad.auth.login;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("login")
	private List<LoginItem> login;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	public List<LoginItem> getLogin(){
		return login;
	}

	public boolean isStatus(){
		return status;
	}
}