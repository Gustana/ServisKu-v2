package com.example.serviceku.remote.model.service.userServiceList;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetUserServiceListResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<GetUserServiceListItem> data;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public List<GetUserServiceListItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"GetUserServiceListResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}