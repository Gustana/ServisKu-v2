package com.example.serviceku.remote.model.inventory;

import com.google.gson.annotations.SerializedName;

public class UpdateInventoryResponse{

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
}