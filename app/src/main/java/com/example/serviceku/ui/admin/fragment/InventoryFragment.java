package com.example.serviceku.ui.admin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.serviceku.R;
import com.example.serviceku.databinding.FragmentInventoryBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.inventory.inventoryList.GetInventoryListResponse;
import com.example.serviceku.ui.admin.AddInventoryActivity;
import com.example.serviceku.ui.admin.adapter.RecyclerAdapterInventory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryFragment extends Fragment {

    private static final String TAG = InventoryFragment.class.getSimpleName();

    private FragmentInventoryBinding binding;

    private ApiClient apiClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_inventory, container, false);

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        binding.rvInventoryAdmin.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        binding.srInventory.setOnRefreshListener(this::getInventoryList);

        getInventoryList();

        binding.fbAddInventory.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), AddInventoryActivity.class);
            startActivity(i);
        });

        return binding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        getInventoryList();
    }

    private void getInventoryList() {
        binding.srInventory.setRefreshing(true);
        apiClient.getInventoryList().enqueue(new Callback<GetInventoryListResponse>() {
            @Override
            public void onResponse(Call<GetInventoryListResponse> call, Response<GetInventoryListResponse> response) {
                Log.i(TAG, "onResponse: inventoryList: "+ response.body().getMessage());

                if(response.isSuccessful() && response.body().getCode()==0){
                    RecyclerAdapterInventory adapter = new RecyclerAdapterInventory(response.body().getData());
                    binding.rvInventoryAdmin.setAdapter(adapter);
                }

                binding.srInventory.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetInventoryListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.getCause());
                binding.srInventory.setRefreshing(false);
            }
        });
    }
}