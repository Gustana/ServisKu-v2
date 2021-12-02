package com.example.serviceku.remote.model.profile;

import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"UpdateProfileResponse{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}