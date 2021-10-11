package com.example.serviceku.ui.user;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.serviceku.MainActivity;
import com.example.serviceku.R;
import com.example.serviceku.databinding.ActivityHomeUserBinding;
import com.example.serviceku.room.DBHolder;
import com.example.serviceku.room.entity.ServiceEntity;
import com.example.serviceku.helper.SPManager;
import com.example.serviceku.ui.aboutUs.AboutUsActivity;
import com.example.serviceku.ui.user.adapter.RecyclerAdapterServiceUser;
import com.example.serviceku.util.LogoutUtil;

import java.util.List;

public class HomeUserActivity extends AppCompatActivity implements LogoutUtil {

    private final String TAG = HomeUserActivity.class.getSimpleName();
    private ActivityHomeUserBinding binding;
    private DBHolder dbHolder;
    private SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHolder = new DBHolder(this);
        spManager = new SPManager(this);

        binding.rvUserServiceList.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        getServiceList();

        binding.btnAdd.setOnClickListener(v -> {
            Intent i = new Intent(this, InputServiceActivity.class);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getServiceList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuLogout){
            logout();
            return true;
        }else if(item.getItemId() == R.id.menuAboutUs){
            startActivity(new Intent(this, AboutUsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void logout() {
        spManager.clearSP();
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void getServiceList() {
        int idUser = spManager.getIdUser();
        class GetService extends AsyncTask<Void, Void, List<ServiceEntity>> {

            @Override
            protected List<ServiceEntity> doInBackground(Void... voids) {
                return dbHolder.getAppDB().serviceDao().getUserServiceList(idUser);
            }

            @Override
            protected void onPostExecute(List<ServiceEntity> serviceEntities) {
                super.onPostExecute(serviceEntities);

                Log.i(TAG, "onPostExecute: idUser: " + idUser);
                Log.i(TAG, "onPostExecute: " + serviceEntities.toString());

                binding.rvUserServiceList.setAdapter(new RecyclerAdapterServiceUser(serviceEntities));
            }
        }

        new GetService().execute();
    }
}