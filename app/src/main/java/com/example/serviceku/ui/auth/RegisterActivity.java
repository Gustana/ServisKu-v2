package com.example.serviceku.ui.auth;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.serviceku.R;
import com.example.serviceku.UnitTesting.ActivityUtil;
import com.example.serviceku.UnitTesting.ProfilPresenter;
import com.example.serviceku.UnitTesting.ProfilView;
import com.example.serviceku.databinding.ActivityRegisterBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.auth.register.RegisterResponse;
import com.example.serviceku.util.RegisterUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity implements RegisterUtil, ProfilView {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    private ActivityRegisterBinding binding;

    private ApiClient apiClient;

    private ProfilPresenter presenter;
    private EditText etEmail, etPassword, etName, etPhoneNo;
    private RadioGroup rbGender;
    private RadioButton rblakilaki, rbperempuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        binding.btnLogin.setOnClickListener(v -> register());

        etEmail = findViewById(R.id.edtEmail);
        etPassword = findViewById(R.id.edtPassword);
        etName = findViewById(R.id.edtName);
        etPhoneNo = findViewById(R.id.edtPhoneNo);
        rbGender = findViewById(R.id.rgJenisKelamin);
        rblakilaki = findViewById(R.id.rbLakiLaki);
        rbperempuan = findViewById(R.id.rbPerempuan);
    }

    private String getSelectedGender() {
        String selectedGender="";

        RadioButton rb = findViewById(binding.rgJenisKelamin.getCheckedRadioButtonId());

        if(rb != null){
            selectedGender = rb.getText().toString();
        }

        Log.i(TAG, "getSelectedGender: " + selectedGender);

        return selectedGender;
    }

    @Override
    public void register() {
        String email = binding.edtEmail.getText().toString();
        String password = binding.edtPassword.getText().toString();
        String phoneNo = binding.edtNo.getText().toString();
        String name = binding.edtNama.getText().toString();
        String gender = getSelectedGender();

        if (email.equals("")) {
            Toast.makeText(this, "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }else if(password.equals("")){
            Toast.makeText(this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }else if(name.equals("")){
            Toast.makeText(this, "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }else if(phoneNo.equals("")){
            Toast.makeText(this, "Nomor Telepon Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }else if(gender.equals("")){
            Toast.makeText(this, "Jenis Kelamin Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<RegisterResponse> registerCall = apiClient.register(email, password, phoneNo, name, gender);

        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.i(TAG, "onResponse: " + response.body());

                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Opps.. unexpected error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

        presenter.onProfilClicked();

    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public void showEmailError(String message) {
        etEmail.setError(message);
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void showPasswordError(String message) {
        etPassword.setError(message);
    }

    @Override
    public String getNama() {
        return etName.getText().toString();
    }

    @Override
    public void showNamaError(String message) {
        etName.setError(message);
    }

    @Override
    public String getPhoneNo() {
        return etPhoneNo.getText().toString();
    }

    @Override
    public void showPhoneNoError(String message) {
        etPhoneNo.setError(message);
    }

    @Override
    public String getGender() {
        if(!rblakilaki.isChecked() && !rbperempuan.isChecked()){
            return "Gender kosong";
        }else if(rblakilaki.isChecked()){
            return "Laki-Laki";
        }else{
            return "Perempuan";
        }
    }

    @Override
    public void showGenderError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startMainProfil() {
        new ActivityUtil(this).startMainProfil();
    }

    @Override
    public void showProfilError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show(); }

    @Override
    public void showErrorResponse(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}