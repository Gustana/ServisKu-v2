package com.example.serviceku.ui.user;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviceku.databinding.ActivityInputServiceBinding;
import com.example.serviceku.helper.SPManager;
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

    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInputServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        spManager = new SPManager(this);

        binding.btnSendService.setOnClickListener(v -> {

            RadioButton rb = findViewById(binding.rgVehicleType.getCheckedRadioButtonId());

            String noPlat = binding.edtNoPlat.getText().toString();
            String problem = binding.edtProblem.getText().toString();
            String vehicleType = rb.getText().toString();

            apiClient.insertService(
                    noPlat,
                    spManager.getIdUser(),
                    problem,
                    new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()),
                    vehicleType
            ).enqueue(new Callback<InsertServiceResponse>() {
                @Override
                public void onResponse(Call<InsertServiceResponse> call, Response<InsertServiceResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(InputServiceActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<InsertServiceResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t.getCause());
                }
            });

        });
    }
}