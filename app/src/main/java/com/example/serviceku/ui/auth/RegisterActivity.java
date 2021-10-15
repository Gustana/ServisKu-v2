package com.example.serviceku.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.serviceku.databinding.ActivityRegisterBinding;
import com.example.serviceku.room.DBHolder;
import com.example.serviceku.room.entity.UserEntity;
import com.example.serviceku.util.RegisterUtil;

public class RegisterActivity extends Activity implements RegisterUtil {

    private ActivityRegisterBinding binding;
    private DBHolder dbHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHolder = new DBHolder(this);

        binding.btnLogin.setOnClickListener(v -> register());
    }

    @Override
    public void register() {
        String username = binding.edtUsername.getText().toString();
        String password = binding.edtPassword.getText().toString();

        class InsertUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                UserEntity userEntity = new UserEntity();

                userEntity.setUsername(username);
                userEntity.setPassword(password);

                dbHolder.getAppDB().userDao().insertUser(userEntity);

                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Berhasil register", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        }

        new InsertUser().execute();
    }
}