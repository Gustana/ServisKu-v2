package com.example.serviceku.remote.model.inventory.inventoryList;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetInventoryListResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<GetInventoryListItem> data;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public List<GetInventoryListItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}