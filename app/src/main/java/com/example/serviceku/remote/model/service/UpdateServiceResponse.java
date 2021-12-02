package com.example.serviceku.remote.model.service;

import com.google.gson.annotations.SerializedName;

public class UpdateServiceResponse{

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
			"UpdateServiceResponse{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}