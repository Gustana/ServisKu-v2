package com.example.serviceku.remote.model.profile.profileDetail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetProfileResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<GetProfileItem> data;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public List<GetProfileItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"GetProfileResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}