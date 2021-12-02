package com.example.serviceku.ui.admin;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviceku.BR;
import com.example.serviceku.R;
import com.example.serviceku.databinding.ActivityDetailServiceBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.service.UpdateServiceResponse;
import com.example.serviceku.remote.model.service.serviceDetail.GetServiceDetailItem;
import com.example.serviceku.remote.model.service.serviceDetail.GetServiceDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailServiceActivity extends AppCompatActivity {

    private static final String TAG = DetailServiceActivity.class.getSimpleName();
    private ActivityDetailServiceBinding binding;

    private int serviceId;

    private ApiClient apiClient;

    private int statusService;
    private float totalPrice = 0F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        serviceId = getIntent().getIntExtra("idService", 0);

        Log.i(TAG, "onCreate: serviceId: " + serviceId);

        getServiceDetail();

        binding.btnUpdateService.setOnClickListener(v -> {

            RadioButton rb = findViewById(binding.rgVehicleType.getCheckedRadioButtonId());

            statusService = generateStatus(rb);

            if (statusService == 2) {
                if (binding.edtTotalPrice.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Total price kosong", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    totalPrice = Float.parseFloat(binding.edtTotalPrice.getText().toString());
                }
            }

            updateService();

        });
    }

    private int generateStatus(RadioButton rb) {
        return (rb.getText().toString().equals(getString(R.string.sedang_diservis))) ? 1 : 2;
    }

    private void updateService() {
        apiClient.updateService(serviceId, statusService, totalPrice).enqueue(new Callback<UpdateServiceResponse>() {
            @Override
            public void onResponse(Call<UpdateServiceResponse> call, Response<UpdateServiceResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getMessage() != null) {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<UpdateServiceResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });
    }

    private void getServiceDetail() {
        apiClient.getServiceDetail(serviceId).enqueue(new Callback<GetServiceDetailResponse>() {
            @Override
            public void onResponse(Call<GetServiceDetailResponse> call, Response<GetServiceDetailResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().getMessage());

                if (response.isSuccessful() && response.body().getCode() == 0) {
                    Log.i(TAG, "onResponse: " + response.body().getData().toString());
                    GetServiceDetailItem serviceItem = response.body().getData().get(0);

                    binding.setVariable(BR.serviceDetailData, serviceItem);
                    binding.executePendingBindings();
                }
            }

            @Override
            public void onFailure(Call<GetServiceDetailResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });
    }

}