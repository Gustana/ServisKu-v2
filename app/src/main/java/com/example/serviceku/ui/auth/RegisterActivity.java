package com.example.serviceku.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.serviceku.R;
import com.example.serviceku.databinding.ActivityRegisterBinding;
import com.example.serviceku.util.RegisterUtil;

public class RegisterActivity extends AppCompatActivity implements RegisterUtil {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v->register());
    }

    @Override
    public void register() {

    }
}