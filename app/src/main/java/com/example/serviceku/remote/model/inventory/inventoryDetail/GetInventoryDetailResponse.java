package com.example.serviceku.remote.model.inventory.inventoryDetail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetInventoryDetailResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<GetInventoryDetailItem> data;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public List<GetInventoryDetailItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"GetInventoryDetailResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}