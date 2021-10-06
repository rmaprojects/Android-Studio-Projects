package project.raka.m.a.notepad.auth.register;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Register{

	@SerializedName("code")
	private int code;

	@SerializedName("userbaru")
	private List<UserbaruItem> userbaru;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public List<UserbaruItem> getUserbaru(){
		return userbaru;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Register{" + 
			"code = '" + code + '\'' + 
			",userbaru = '" + userbaru + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}