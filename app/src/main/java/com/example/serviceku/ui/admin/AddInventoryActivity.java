package com.example.serviceku.ui.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviceku.databinding.ActivityAddInventoryBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.inventory.InsertInventoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInventoryActivity extends AppCompatActivity {

    private static final String TAG = AddInventoryActivity.class.getSimpleName();

    private ActivityAddInventoryBinding binding;

    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        binding.btnAddInventory.setOnClickListener(v -> {
            String inventoryName = binding.edtInventoryName.getText().toString();
            int inventoryAmount = Integer.parseInt(binding.edtInventoryAmount.getText().toString());
            String sparepartType = binding.edtInventoryType.getText().toString();
            float inventoryPrice = Float.parseFloat(binding.edtInventoryPrice.getText().toString());

            apiClient.insertInventory(
                    inventoryName,
                    inventoryAmount,
                    sparepartType,
                    inventoryPrice
            ).enqueue(new Callback<InsertInventoryResponse>() {
                @Override
                public void onResponse(Call<InsertInventoryResponse> call, Response<InsertInventoryResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddInventoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<InsertInventoryResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t.getCause());
                }
            });
        });
    }
}