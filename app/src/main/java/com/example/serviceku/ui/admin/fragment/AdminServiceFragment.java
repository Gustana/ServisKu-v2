package com.example.serviceku.ui.admin.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.serviceku.R;
import com.example.serviceku.databinding.FragmentAdminServiceBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.service.serviceList.GetServiceListResponse;
import com.example.serviceku.ui.admin.adapter.RecyclerAdapterServiceAdmin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminServiceFragment extends Fragment {

    private static final String TAG = AdminServiceFragment.class.getSimpleName();

    private FragmentAdminServiceBinding binding;

    private ApiClient apiClient;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_service, container, false);

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);


        binding.rvServiceAdmin.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        binding.srService.setOnRefreshListener(this::getServiceList);

        getServiceList();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getServiceList();
    }

    private void getServiceList() {
        binding.srService.setRefreshing(true);
        apiClient.getServiceList().enqueue(new Callback<GetServiceListResponse>() {
            @Override
            public void onResponse(Call<GetServiceListResponse> call, Response<GetServiceListResponse> response) {
                Log.i(TAG, "onResponse: serviceList: " + response.body().getMessage());

                if (response.isSuccessful() && response.body().getCode() == 0) {
                    RecyclerAdapterServiceAdmin adapter = new RecyclerAdapterServiceAdmin(response.body().getData());
                    binding.rvServiceAdmin.setAdapter(adapter);
                }

                binding.srService.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetServiceListResponse> call, Throwable t) {
                binding.srService.setRefreshing(false);
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });
    }

}