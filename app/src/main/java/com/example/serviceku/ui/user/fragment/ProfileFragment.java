package com.example.serviceku.ui.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.serviceku.R;
import com.example.serviceku.databinding.FragmentProfileBinding;
import com.example.serviceku.helper.SPManager;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.profile.UpdateProfileResponse;
import com.example.serviceku.remote.model.profile.profileDetail.GetProfileItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();

    private FragmentProfileBinding binding;

    private ApiClient apiClient;

    private SPManager spManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        setProfile();

        binding.btnUpdateProfile.setOnClickListener(v -> {
            String name = binding.edtName.getText().toString();
            String gender = getSelectedGender();
            String phoneNo = binding.edtPhoneNo.getText().toString();

            apiClient.updateProfile(
                    spManager.getIdUser(),
                    phoneNo,
                    name,
                    gender
            ).enqueue(new Callback<UpdateProfileResponse>() {
                @Override
                public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {

                    Log.i(TAG, "onResponse: updateProfile: " + response.body().getCode());

                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        GetProfileItem getProfileItem = new GetProfileItem(
                                phoneNo,
                                name,
                                spManager.getLevelUser(),
                                String.valueOf(spManager.getIdUser()),
                                gender,
                                spManager.getEmail()
                        );

                        Log.i(TAG, "onResponse: " + gender);

                        spManager.setLogin(getProfileItem);
                    }
                }

                @Override
                public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t.getCause());
                }
            });
        });

        return binding.getRoot();
    }

    private void setProfile() {
        binding.edtEmail.setText(spManager.getEmail());
        binding.edtPhoneNo.setText(spManager.getPhoneNo());
        binding.edtName.setText(spManager.getName());
        setGender();
    }

    private void setGender() {
        if(spManager.getGender().equalsIgnoreCase("Perempuan")){
            binding.rbPerempuan.setChecked(true);
        }else{
            binding.rbLakilaki.setChecked(true);
        }
    }

    private String getSelectedGender() {
        String selectedGender;

        RadioButton rb = requireView().findViewById(binding.rgJenisKelamin.getCheckedRadioButtonId());

        selectedGender = rb.getText().toString();

        Log.i(TAG, "getSelectedGender: " + selectedGender);

        return selectedGender;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        spManager = new SPManager(context);
    }
}