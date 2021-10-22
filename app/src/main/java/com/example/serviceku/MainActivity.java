package com.example.serviceku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviceku.databinding.ActivityMainBinding;
import com.example.serviceku.helper.SPManager;
import com.example.serviceku.ui.admin.HomeAdminActivity;
import com.example.serviceku.ui.auth.LoginActivity;
import com.example.serviceku.ui.auth.RegisterActivity;
import com.example.serviceku.ui.user.HomeUserActivity;
import com.example.serviceku.util.LoginUtil;
import com.example.serviceku.util.RegisterUtil;

public class MainActivity extends Activity implements LoginUtil, RegisterUtil {

    private SPManager spManager;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        spManager = new SPManager(this);

        checkLogin();

        binding.btnLogin.setOnClickListener(v -> login());
        binding.btnRegister.setOnClickListener(v -> register());

    }

    private void checkLogin() {
        if (spManager.isLoggedIn()) {
            if (spManager.isLevelAdmin()) {
                Intent i = new Intent(this, HomeAdminActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            } else {
                Intent i = new Intent(this, HomeUserActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        }
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