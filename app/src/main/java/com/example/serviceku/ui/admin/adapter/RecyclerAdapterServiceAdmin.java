package com.example.serviceku.ui.admin.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceku.BR;
import com.example.serviceku.R;
import com.example.serviceku.databinding.RecyclerItemAdminServiceBinding;
import com.example.serviceku.remote.model.service.serviceList.GetServiceListItem;
import com.example.serviceku.ui.admin.DetailServiceActivity;

import java.util.List;

public class RecyclerAdapterServiceAdmin extends RecyclerView.Adapter<RecyclerAdapterServiceAdmin.UI> {
    private final List<GetServiceListItem> serviceList;
    private String username;

    public RecyclerAdapterServiceAdmin(List<GetServiceListItem> serviceList) {
        this.serviceList = serviceList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemAdminServiceBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_item_admin_service,
                parent,
                false
        );

        return new UI(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {

        GetServiceListItem serviceData = serviceList.get(position);
        String status;

        holder.bind(serviceData);

        if (Integer.parseInt(serviceData.getStatusService()) == 0) {
            status = holder.binding.getRoot().getResources().getString(R.string.belum_diservis);
        } else if (Integer.parseInt(serviceData.getStatusService()) == 1) {
            status = holder.binding.getRoot().getResources().getString(R.string.sedang_diservis);
        } else {
            status = holder.binding.getRoot().getResources().getString(R.string.selesai_service);
        }

        if (serviceData.getJenisKendaraan().equalsIgnoreCase("Motor")) {
            holder.binding.imgVehicle.setImageDrawable(ResourcesCompat.getDrawable(holder.binding.getRoot().getResources(), R.drawable.motor, null));
        } else {
            holder.binding.imgVehicle.setImageDrawable(ResourcesCompat.getDrawable(holder.binding.getRoot().getResources(), R.drawable.mobil, null));
        }

        holder.binding.txtStatus.setText(status);

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(holder.binding.getRoot().getContext(), DetailServiceActivity.class);
            i.putExtra("idService", Integer.valueOf(serviceData.getIdService()));
            holder.binding.getRoot().getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }


    static class UI extends RecyclerView.ViewHolder {
        private final RecyclerItemAdminServiceBinding binding;

        public UI(RecyclerItemAdminServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.serviceData, obj);
            binding.executePendingBindings();
        }
    }

}
