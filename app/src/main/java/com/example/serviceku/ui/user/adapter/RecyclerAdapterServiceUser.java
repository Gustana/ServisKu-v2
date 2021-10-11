package com.example.serviceku.ui.user.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceku.BR;
import com.example.serviceku.R;
import com.example.serviceku.databinding.RecyclerItemUserServiceBinding;
import com.example.serviceku.room.entity.ServiceEntity;

import java.util.List;

public class RecyclerAdapterServiceUser extends RecyclerView.Adapter<RecyclerAdapterServiceUser.UI> {

    private static final String TAG = RecyclerAdapterServiceUser.class.getSimpleName();
    private final List<ServiceEntity> serviceEntityList;

    public RecyclerAdapterServiceUser(List<ServiceEntity> serviceEntityList) {
        this.serviceEntityList = serviceEntityList;
        Log.i(TAG, "RecyclerAdapterServiceUser: " + serviceEntityList);
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
        return new UI(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {
        ServiceEntity serviceEntity = serviceEntityList.get(position);

        holder.bind(serviceEntity);

        holder.binding.txtNoPlat.setText(serviceEntity.getNoPlat());
    }

    @Override
    public int getItemCount() {
        return serviceEntityList.size();
    }

    static class UI extends RecyclerView.ViewHolder {
        private final RecyclerItemUserServiceBinding binding;

        public UI(@NonNull RecyclerItemUserServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.serviceUserData, obj);
            binding.executePendingBindings();
        }

    }
}
