package com.example.serviceku.ui.user;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviceku.databinding.ActivityInputServiceBinding;
import com.example.serviceku.room.DBHolder;
import com.example.serviceku.room.entity.ServiceEntity;
import com.example.serviceku.helper.SPManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InputServiceActivity extends AppCompatActivity {

    private ActivityInputServiceBinding binding;
    private DBHolder dbHolder;
    private SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInputServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHolder = new DBHolder(this);
        spManager = new SPManager(this);

        binding.btnSendService.setOnClickListener(v -> {

            RadioButton rb = findViewById(binding.rgVehicleType.getCheckedRadioButtonId());

            String noPlat = binding.edtNoPlat.getText().toString();
            String noHP = binding.edtPhoneNo.getText().toString();
            String problem = binding.edtProblem.getText().toString();
            String vehicleType = rb.getText().toString();

            class InsertService extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

                    ServiceEntity serviceEntity = new ServiceEntity(
                            spManager.getIdUser(),
                            noPlat,
                            problem,
                            dateFormat.format(date),
                            noHP,
                            0,
                            vehicleType,
                            0
                    );
                    dbHolder.getAppDB().serviceDao().insertService(serviceEntity);
                    return null;
                }

                @Override
                protected void onPostExecute(Void unused) {
                    super.onPostExecute(unused);
                    Toast.makeText(getApplicationContext(), "Berhasil Kirim", Toast.LENGTH_SHORT).show();
                }
            }

            new InsertService().execute();
        });
    }
}