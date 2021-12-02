package com.example.serviceku.remote.model.service.serviceDetail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetServiceDetailResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<GetServiceDetailItem> data;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public List<GetServiceDetailItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"GetServiceDetailResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}