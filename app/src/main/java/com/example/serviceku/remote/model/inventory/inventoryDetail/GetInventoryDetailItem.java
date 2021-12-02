package com.example.serviceku.remote.model.inventory.inventoryDetail;

import com.google.gson.annotations.SerializedName;

public class GetInventoryDetailItem {

	@SerializedName("jenis_sparepart")
	private String jenisSparepart;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("harga")
	private String harga;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("id_inventori")
	private String idInventori;

	@SerializedName("gambar")
	private String gambar;

	public String getJenisSparepart(){
		return jenisSparepart;
	}

	public String getJumlah(){
		return jumlah;
	}

	public String getHarga(){
		return harga;
	}

	public String getNamaBarang(){
		return namaBarang;
	}

	public String getIdInventori(){
		return idInventori;
	}

	public String getGambar(){
		return gambar;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"jenis_sparepart = '" + jenisSparepart + '\'' + 
			",jumlah = '" + jumlah + '\'' + 
			",harga = '" + harga + '\'' + 
			",nama_barang = '" + namaBarang + '\'' + 
			",id_inventori = '" + idInventori + '\'' + 
			",gambar = '" + gambar + '\'' + 
			"}";
		}
}