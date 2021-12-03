package com.example.serviceku.remote;

import com.example.serviceku.remote.model.auth.login.LoginResponse;
import com.example.serviceku.remote.model.auth.register.RegisterResponse;
import com.example.serviceku.remote.model.inventory.DeleteInventoryResponse;
import com.example.serviceku.remote.model.inventory.InsertInventoryResponse;
import com.example.serviceku.remote.model.inventory.UpdateInventoryResponse;
import com.example.serviceku.remote.model.inventory.inventoryDetail.GetInventoryDetailResponse;
import com.example.serviceku.remote.model.inventory.inventoryList.GetInventoryListResponse;
import com.example.serviceku.remote.model.profile.UpdateProfileResponse;
import com.example.serviceku.remote.model.profile.profileDetail.GetProfileResponse;
import com.example.serviceku.remote.model.service.DeleteServiceResponse;
import com.example.serviceku.remote.model.service.InsertServiceResponse;
import com.example.serviceku.remote.model.service.UpdateServiceResponse;
import com.example.serviceku.remote.model.service.serviceDetail.GetServiceDetailResponse;
import com.example.serviceku.remote.model.service.serviceList.GetServiceListResponse;
import com.example.serviceku.remote.model.service.userServiceList.GetUserServiceListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClient {

    @FormUrlEncoded
    @POST("auth/login.php")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @FormUrlEncoded
    @POST("auth/register.php")
    Call<RegisterResponse> register(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("no_hp") String phoneNo,
            @Field("nama") String name,
            @Field("jenis_kelamin") String gender
    );

    @FormUrlEncoded
    @POST("service/insertService.php")
    Call<InsertServiceResponse> insertService(
            @Field("no_plat") String vehicleNo,
            @Field("id_user") int idUser,
            @Field("keluhan") String problem,
            @Field("tanggal") String date,
            @Field("jenis_kendaraan") String vehicleType
    );

    @FormUrlEncoded
    @POST("service/updateService.php")
    Call<UpdateServiceResponse> updateService(
            @Field("id_service") int idService,
            @Field("status_service") int serviceStatus,
            @Field("total_harga") float totalPrice
    );

    @FormUrlEncoded
    @POST("service/deleteService.php")
    Call<DeleteServiceResponse> deleteService(
            @Field("id_service") int idService
    );

    @GET("service/getUserServiceList.php")
    Call<GetUserServiceListResponse> getUserServiceList(@Query("id_user") int idUser);

    @GET("service/getServiceList.php")
    Call<GetServiceListResponse> getServiceList();

    @GET("service/getServiceDetail.php")
    Call<GetServiceDetailResponse> getServiceDetail(@Query("id_service") int idService);

    @FormUrlEncoded
    @POST("inventory/insertInventory.php")
    Call<InsertInventoryResponse> insertInventory(
            @Field("nama_barang") String inventoryName,
            @Field("jumlah") int inventoryAmount,
            @Field("jenis_sparepart") String sparepartType,
            @Field("harga") float price,
            @Field("image") String image
    );

    @FormUrlEncoded
    @POST("inventory/updateInventory.php")
    Call<UpdateInventoryResponse> updateInventory(
            @Field("id_inventory") int idInventory,
            @Field("nama_barang") String inventoryName,
            @Field("jumlah") int inventoryAmount,
            @Field("jenis_sparepart") String sparepartType,
            @Field("harga") float price
    );

    @FormUrlEncoded
    @POST("inventory/deleteInventory.php")
    Call<DeleteInventoryResponse> deleteInventory(
            @Field("id_inventory") int idInventory
    );

    @GET("inventory/getInventoryList.php")
    Call<GetInventoryListResponse> getInventoryList();

    @GET("inventory/getInventoryDetail.php")
    Call<GetInventoryDetailResponse> getInventoryDetail(@Query("id_inventory") int idInventory);

    @GET("profile/getProfile.php")
    Call<GetProfileResponse> getProfile(@Query("id_user") int idUser);

    @FormUrlEncoded
    @POST("profile/updateProfile.php")
    Call<UpdateProfileResponse> updateProfile(
            @Field("id_user") int idUser,
            @Field("pass") String pass,
            @Field("no_hp") String phoneNo,
            @Field("nama") String name,
            @Field("jenis_kelamin") String gender
    );

}
