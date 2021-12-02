package com.example.serviceku.ui.user.adapter;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceku.BR;
import com.example.serviceku.R;
import com.example.serviceku.databinding.RecyclerItemUserServiceBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.service.DeleteServiceResponse;
import com.example.serviceku.remote.model.service.userServiceList.GetUserServiceListItem;
import com.example.serviceku.ui.user.fragment.UserServiceFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapterServiceUser extends RecyclerView.Adapter<RecyclerAdapterServiceUser.UI> {

    private static final String TAG = RecyclerAdapterServiceUser.class.getSimpleName();
    private final List<GetUserServiceListItem> serviceList;

    private ApiClient apiClient;

    public RecyclerAdapterServiceUser(List<GetUserServiceListItem> serviceList) {
        this.serviceList = serviceList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemUserServiceBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_item_user_service,
                parent,
                false
        );

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        return new UI(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {
        String status;
        GetUserServiceListItem serviceData = serviceList.get(position);

        holder.bind(serviceData);

        if (serviceData.getJenisKendaraan().equalsIgnoreCase("Motor")) {
            holder.binding.imgVehicle.setImageDrawable(ResourcesCompat.getDrawable(holder.binding.getRoot().getResources(), R.drawable.motor, null));
        } else {
            holder.binding.imgVehicle.setImageDrawable(ResourcesCompat.getDrawable(holder.binding.getRoot().getResources(), R.drawable.mobil, null));
        }

        if (Integer.parseInt(serviceData.getStatusService()) == 0) {
            status = holder.binding.getRoot().getResources().getString(R.string.belum_diservis);
        } else if (Integer.parseInt(serviceData.getStatusService()) == 1) {
            status = holder.binding.getRoot().getResources().getString(R.string.sedang_diservis);
        } else {
            status = holder.binding.getRoot().getResources().getString(R.string.selesai_service);
        }

        holder.binding.txtStatus.setText(status);

        holder.itemView.setOnLongClickListener(view -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(holder.itemView.getContext());
            alertDialog.setMessage("Yakin Hapus?");
            alertDialog.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                apiClient.deleteService(Integer.parseInt(serviceData.getIdService())).enqueue(new Callback<DeleteServiceResponse>() {
                    @Override
                    public void onResponse(Call<DeleteServiceResponse> call, Response<DeleteServiceResponse> response) {
                        Log.i(TAG, "onResponse: delete: " + response.body().getCode());
                        if (response.isSuccessful()){
                            Toast.makeText(holder.itemView.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteServiceResponse> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t.getCause());
                    }
                });
            });
            alertDialog.setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> {
                dialogInterface.dismiss();
            });

            alertDialog.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    static class UI extends RecyclerView.ViewHolder {
        private final RecyclerItemUserServiceBinding binding;

        public UI(@NonNull RecyclerItemUserServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.serviceData, obj);
            binding.executePendingBindings();
        }

    }
}
