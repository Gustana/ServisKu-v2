package com.example.serviceku.ui.admin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviceku.BR;
import com.example.serviceku.databinding.ActivityDetailInventoryBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.inventory.UpdateInventoryResponse;
import com.example.serviceku.remote.model.inventory.inventoryDetail.GetInventoryDetailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInventoryActivity extends AppCompatActivity {

    private static final String TAG = DetailInventoryActivity.class.getSimpleName();

    private ActivityDetailInventoryBinding binding;

    private ApiClient apiClient;

    private int idInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        idInventory = Integer.parseInt(getIntent().getStringExtra("idInventory"));

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);
        apiClient.getInventoryDetail(idInventory).enqueue(new Callback<GetInventoryDetailResponse>() {
            @Override
            public void onResponse(Call<GetInventoryDetailResponse> call, Response<GetInventoryDetailResponse> response) {
                if (response.isSuccessful()) {
                    binding.setVariable(BR.inventoryDetailData, response.body().getData().get(0));
                    binding.executePendingBindings();
                }
            }

            @Override
            public void onFailure(Call<GetInventoryDetailResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });

        binding.btnUpdateInventory.setOnClickListener(view -> {
            String inventoryName = binding.edtInventoryDetailName.getText().toString();
            int inventoryAmount = Integer.parseInt(binding.edtInventoryDetailAmount.getText().toString());
            String sparepartType = binding.edtInventoryDetailType.getText().toString();
            float inventoryPrice = Float.parseFloat(binding.edtInventoryDetailPrice.getText().toString());

            apiClient.updateInventory(
                    idInventory,
                    inventoryName,
                    inventoryAmount,
                    sparepartType,
                    inventoryPrice
            ).enqueue(new Callback<UpdateInventoryResponse>() {
                @Override
                public void onResponse(Call<UpdateInventoryResponse> call, Response<UpdateInventoryResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(DetailInventoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UpdateInventoryResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t.getCause());
                }
            });
        });
    }
}