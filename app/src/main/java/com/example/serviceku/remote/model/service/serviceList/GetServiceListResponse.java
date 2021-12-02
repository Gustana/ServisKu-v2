package com.example.serviceku.remote.model.service.serviceList;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetServiceListResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<GetServiceListItem> data;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public List<GetServiceListItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}