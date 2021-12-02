package com.example.serviceku.remote.model.auth.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("code")
	private String code;

	@SerializedName("message")
	private String message;

	public String getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}