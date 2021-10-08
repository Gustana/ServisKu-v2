package com.example.serviceku.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviceku.R;
import com.example.serviceku.databinding.ActivityLoginBinding;
import com.example.serviceku.helper.SPManager;
import com.example.serviceku.ui.admin.HomeAdminActivity;
import com.example.serviceku.util.LoginUtil;

public class LoginActivity extends AppCompatActivity implements LoginUtil {

    private ActivityLoginBinding binding;
    private SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        spManager = new SPManager(this);

        binding.btnLogin.setOnClickListener(v -> login());

    }

    @Override
    public void login() {
        String username = binding.edtUsername.getText().toString();
        String password = binding.edtPassword.getText().toString();

        if (username.equals(getString(R.string.admin)) && password.equals(getString(R.string.admin))) {
            Intent i = new Intent(this, HomeAdminActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}