package com.example.serviceku.UnitTesting;

import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.model.auth.register.Register;
import com.example.serviceku.remote.model.auth.register.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilService {

    public void profil(final ProfilView view, String email, String password, String phoneNo, String name, String gender, final ProfilCallback callback){
        ApiClient apiService = ApiInstance.getRetrofitInstance().create(ApiClient.class);
        Call<RegisterResponse> profilDAOCall = apiService.register(email, password, phoneNo, name, gender);

        profilDAOCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.body().getMessage().equalsIgnoreCase("berhasil daftar")){
                    callback.onSuccess(true, email, password, phoneNo, name, gender);
                }
                else{
                    callback.onError();
                    view.showProfilError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                view.showErrorResponse(t.getMessage());
                callback.onError();
            }
        });
    }

    public Boolean getValid(final ProfilView view, String email, String password, String phoneNo, String name, String gender) {
        final Boolean[] bool = new Boolean[1];
        profil(view, email,  password,  phoneNo,  name,  gender, new ProfilCallback() {
            @Override
            public void onSuccess(boolean value, String email, String password, String phoneNo, String name, String gender) {
                bool[0] = true;
            }

            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }
}