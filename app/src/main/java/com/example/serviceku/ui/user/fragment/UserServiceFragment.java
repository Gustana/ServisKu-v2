package com.example.serviceku.ui.user.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.example.serviceku.databinding.FragmentUserServiceBinding;
import com.example.serviceku.helper.SPManager;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.service.userServiceList.GetUserServiceListResponse;
import com.example.serviceku.ui.user.InputServiceActivity;
import com.example.serviceku.ui.user.adapter.RecyclerAdapterServiceUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServiceFragment extends Fragment {

    private static final String TAG = UserServiceFragment.class.getSimpleName();

    private FragmentUserServiceBinding binding;
    private SPManager spManager;
    private int idUser;

    private ApiClient apiClient;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_service, container, false);

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        binding.rvServiceUser.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        binding.srService.setOnRefreshListener(this::getServiceList);

        getServiceList();

        binding.fbAddService.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), InputServiceActivity.class);
            startActivity(i);
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getServiceList();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        spManager = new SPManager(context);
    }

    private void getServiceList() {
        binding.srService.setRefreshing(true);

        apiClient.getUserServiceList(spManager.getIdUser()).enqueue(new Callback<GetUserServiceListResponse>() {
            @Override
            public void onResponse(Call<GetUserServiceListResponse> call, Response<GetUserServiceListResponse> response) {
                Log.i(TAG, "onResponse: serviceList: " + response.body().getMessage());

                if (response.isSuccessful() && response.body().getCode() == 0) {
                    RecyclerAdapterServiceUser adapter = new RecyclerAdapterServiceUser(response.body().getData());
                    binding.rvServiceUser.setAdapter(adapter);
                }

                binding.srService.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<GetUserServiceListResponse> call, Throwable t) {
                binding.srService.setRefreshing(false);
                Log.e(TAG, "onFailure: ", t.getCause());
            }
        });

    }
}