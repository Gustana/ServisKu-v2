package com.example.serviceku.remote.model.service.serviceList;

import com.google.gson.annotations.SerializedName;

public class GetServiceListItem {

	@SerializedName("total_harga")
	private String totalHarga;

	@SerializedName("no_plat")
	private String noPlat;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("status_service")
	private String statusService;

	@SerializedName("jenis_kendaraan")
	private String jenisKendaraan;

	@SerializedName("keluhan")
	private String keluhan;

	@SerializedName("id_service")
	private String idService;

	public String getTotalHarga(){
		return totalHarga;
	}

	public String getNoPlat(){
		return noPlat;
	}

	public String getIdUser(){
		return idUser;
	}

	public String getTanggal(){
		return tanggal;
	}

	public String getStatusService(){
		return statusService;
	}

	public String getJenisKendaraan(){
		return jenisKendaraan;
	}

	public String getKeluhan(){
		return keluhan;
	}

	public String getIdService(){
		return idService;
	}
}