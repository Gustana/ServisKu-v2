package com.example.serviceku.ui.admin.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceku.BR;
import com.example.serviceku.R;
import com.example.serviceku.databinding.RecyclerItemAdminServiceBinding;
import com.example.serviceku.db.entity.ServiceEntity;
import com.example.serviceku.ui.admin.DetailServiceActivity;

import java.util.List;

public class RecyclerAdapterServiceAdmin extends RecyclerView.Adapter<RecyclerAdapterServiceAdmin.UI> {
    private final List<ServiceEntity> serviceEntityList;

    public RecyclerAdapterServiceAdmin(List<ServiceEntity> serviceEntityList) {
        this.serviceEntityList = serviceEntityList;
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
        ServiceEntity serviceEntity = serviceEntityList.get(position);

        holder.bind(serviceEntity);

        holder.itemView.setOnClickListener(v->{
            Intent i = new Intent(holder.binding.getRoot().getContext(), DetailServiceActivity.class);
            i.putExtra("idService", serviceEntity.getIdService());
            holder.binding.getRoot().getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return serviceEntityList.size();
    }

    static class UI extends RecyclerView.ViewHolder {
        private final RecyclerItemAdminServiceBinding binding;

        public UI(RecyclerItemAdminServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.serviceAdminData, obj);
            binding.executePendingBindings();
        }
    }
}
