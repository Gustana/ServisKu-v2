package com.example.serviceku.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.serviceku.databinding.ActivityLoginBinding;
import com.example.serviceku.helper.SPManager;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.auth.login.LoginResponse;
import com.example.serviceku.remote.model.profile.profileDetail.GetProfileItem;
import com.example.serviceku.remote.model.profile.profileDetail.GetProfileResponse;
import com.example.serviceku.ui.admin.HomeAdminActivity;
import com.example.serviceku.ui.user.HomeUserActivity;
import com.example.serviceku.util.LoginUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity implements LoginUtil {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding binding;
    private SPManager spManager;
    private int userId;

    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        spManager = new SPManager(this);

        checkSession();

        binding.btnLogin.setOnClickListener(v -> login());

        binding.tvRegisterNow.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));

        binding.btncleartext.setOnClickListener(v -> {
            binding.edtEmail.setText("");
            binding.edtPassword.setText("");
        });

    }

    private void checkSession() {
        if(spManager.isLoggedIn()){
            Intent i;
            if(spManager.isLevelAdmin()){
                i = new Intent(this, HomeAdminActivity.class);
            }else{
                i = new Intent(this, HomeUserActivity.class);
            }

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        }
    }

    @Override
    public void login() {
        String email = binding.edtEmail.getText().toString();
        String password = binding.edtPassword.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Input Kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<LoginResponse> loginCall = apiClient.login(email, password);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i(TAG, "onResponse: " + response.body());


                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.code() != -1) {

                        try {
                            userId = Integer.parseInt(response.body().getCode());

                            Call<GetProfileResponse> profileCall = apiClient.getProfile(userId);
                            profileCall.enqueue(new Callback<GetProfileResponse>() {
                                @Override
                                public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                                    Log.i(TAG, "onResponse: profile: " + response.body());
                                    if (response.isSuccessful() && response.body().getCode() != 52) {

                                        GetProfileItem item = response.body().getData().get(0);
                                        GetProfileItem getProfileItem = new GetProfileItem(
                                                item.getNoHp(),
                                                item.getNama(),
                                                item.getLevelUser(),
                                                item.getIdUser(),
                                                item.getJenisKelamin(),
                                                item.getEmail()
                                        );
                                        spManager.setLogin(getProfileItem);
                                        Log.i(TAG, "onResponse: level " + item.getLevelUser());

                                        Intent i;
                                        if(item.getLevelUser().equals("0")){
                                            i = new Intent(LoginActivity.this, HomeAdminActivity.class);
                                        }else{
                                            i = new Intent(LoginActivity.this, HomeUserActivity.class);
                                        }

                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                    }
                                }

                                @Override
                                public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                                    Log.e(TAG, "onFailure: ", t.getCause());
                                    Log.e(TAG, "onFailure: " + call.request().toString());
                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(TAG, "onResponse: response login null");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

        //TODO: delete this
//        if (email.equals(getString(R.string.admin)) && password.equals(getString(R.string.admin))) {
//            Log.i(TAG, "onPostExecute: login as admin");
//            spManager.setLogin("admin", "admin", SPManager.LEVEL_ADMIN);
//
//            Intent i = new Intent(this, HomeAdminActivity.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(i);
//        } else {
//
//        }
    }

}