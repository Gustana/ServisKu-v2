package com.example.serviceku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.serviceku.ui.auth.LoginActivity;
import com.example.serviceku.ui.auth.RegisterActivity;
import com.example.serviceku.databinding.ActivityMainBinding;
import com.example.serviceku.util.LoginUtil;
import com.example.serviceku.util.RegisterUtil;

public class MainActivity extends AppCompatActivity implements LoginUtil, RegisterUtil {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v-> login());
        binding.btnRegister.setOnClickListener(v-> register());

    }

    @Override
    public void login() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void register() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}