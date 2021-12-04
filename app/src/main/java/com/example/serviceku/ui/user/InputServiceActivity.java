package com.example.serviceku.ui.user;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviceku.databinding.ActivityInputServiceBinding;
import com.example.serviceku.helper.SPManager;
import com.example.serviceku.helper.ServiceInputChecker;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.service.InsertServiceResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputServiceActivity extends AppCompatActivity {

    private static final String TAG = InputServiceActivity.class.getSimpleName();

    private ActivityInputServiceBinding binding;
    private SPManager spManager;

    private String vehicleType = "";

    private ApiClient apiClient;
    private int idUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInputServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        spManager = new SPManager(this);
        idUser = spManager.getIdUser();

        binding.btnSendService.setOnClickListener(v -> {

            RadioButton rb = findViewById(binding.rgVehicleType.getCheckedRadioButtonId());
            if(rb!=null){
                vehicleType = rb.getText().toString();
            }

            String noPlat = binding.edtNoPlat.getText().toString();
            String problem = binding.edtProblem.getText().toString();

            ServiceInputChecker inputChecker = new ServiceInputChecker(noPlat, problem, vehicleType);

            if (inputChecker.getNoPlat().isEmpty() || inputChecker.getVehicleType().isEmpty() && inputChecker.getProblem().isEmpty()) {
                Toast.makeText(InputServiceActivity.this, inputChecker.getEmptyMessage(), Toast.LENGTH_SHORT).show();
            } else {

                apiClient.insertService(
                        noPlat,
                        idUser,
                        problem,
                        new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()),
                        vehicleType
                ).enqueue(new Callback<InsertServiceResponse>() {
                    @Override
                    public void onResponse(Call<InsertServiceResponse> call, Response<InsertServiceResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(InputServiceActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<InsertServiceResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t.getCause());
                    }
                });
            }

        });
    }
}