package com.example.serviceku.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.serviceku.R;
import com.example.serviceku.databinding.ActivityLoginBinding;
import com.example.serviceku.helper.SPManager;
import com.example.serviceku.room.DBHolder;
import com.example.serviceku.room.entity.UserEntity;
import com.example.serviceku.ui.admin.HomeAdminActivity;
import com.example.serviceku.ui.user.HomeUserActivity;
import com.example.serviceku.util.LoginUtil;

public class LoginActivity extends Activity implements LoginUtil {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding binding;
    private SPManager spManager;
    private DBHolder dbHolder;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        spManager = new SPManager(this);
        dbHolder = new DBHolder(this);

        binding.btnLogin.setOnClickListener(v -> login());

        binding.tvRegisterNow.setOnClickListener(v->startActivity(new Intent(this, RegisterActivity.class)));

        binding.btncleartext.setOnClickListener(v->{
            binding.edtUsername.setText("");
            binding.edtPassword.setText("");
        });

    }

    @Override
    public void login() {
        String username = binding.edtUsername.getText().toString();
        String password = binding.edtUsername.getText().toString();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "Input Kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (username.equals(getString(R.string.admin)) && password.equals(getString(R.string.admin))) {
            Log.i(TAG, "onPostExecute: login as admin");
            spManager.setLogin("admin", "admin", SPManager.LEVEL_ADMIN);

            Intent i = new Intent(this, HomeAdminActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {

            class GetUser extends AsyncTask<Void, Void, UserEntity> {

                @Override
                protected UserEntity doInBackground(Void... voids) {
                    return dbHolder.getAppDB().userDao().getUser(username);
                }

                @Override
                protected void onPostExecute(UserEntity userEntity) {
                    super.onPostExecute(userEntity);
                    if (userEntity == null) {
                        Toast.makeText(getApplicationContext(), "Belum terdaftar", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.equals(userEntity.getPassword())) {
                            Log.i(TAG, "onPostExecute: login as User");

                            userId = dbHolder.getAppDB().userDao().getIdUser(username);

                            Log.i(TAG, "onPostExecute: userId: " + userId);
                            spManager.setLogin(userId, username, password, SPManager.LEVEL_USER);

                            Intent i = new Intent(getApplicationContext(), HomeUserActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            new GetUser().execute();
        }
    }
}