package com.example.serviceku.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.serviceku.R;
import com.example.serviceku.databinding.ActivityHomeAdminBinding;

public class HomeAdminActivity extends AppCompatActivity {

    private ActivityHomeAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}