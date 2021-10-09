package com.example.serviceku.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.serviceku.R;
import com.example.serviceku.databinding.ActivityDetailServiceBinding;

public class DetailServiceActivity extends AppCompatActivity {

    private ActivityDetailServiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUpdateService.setOnClickListener(v->{

        });
    }
}