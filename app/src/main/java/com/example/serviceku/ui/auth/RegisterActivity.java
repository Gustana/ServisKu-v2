package com.example.serviceku.ui.auth;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.serviceku.databinding.ActivityRegisterBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.auth.register.RegisterResponse;
import com.example.serviceku.util.RegisterUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity implements RegisterUtil {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    private ActivityRegisterBinding binding;

    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        binding.btnLogin.setOnClickListener(v -> register());
    }

    @Override
    public void register() {
        String email = binding.edtEmail.getText().toString();
        String password = binding.edtPassword.getText().toString();
        String phoneNo = "";
        String name = "";
        String gender = "";

        Call<RegisterResponse> registerCall = apiClient.register(email, password, phoneNo, name, gender);

        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.i(TAG, "onResponse: " + response.body());

                if(response.isSuccessful() && response.code()==0){

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}