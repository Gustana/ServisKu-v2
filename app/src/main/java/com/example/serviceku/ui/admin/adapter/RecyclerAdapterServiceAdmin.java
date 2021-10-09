package com.example.serviceku.ui.admin.adapter;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceku.BR;
import com.example.serviceku.R;
import com.example.serviceku.databinding.RecyclerItemAdminServiceBinding;
import com.example.serviceku.db.DBHolder;
import com.example.serviceku.db.entity.ServiceEntity;
import com.example.serviceku.ui.admin.DetailServiceActivity;

import java.util.List;

public class RecyclerAdapterServiceAdmin extends RecyclerView.Adapter<RecyclerAdapterServiceAdmin.UI> {
    private final List<ServiceEntity> serviceEntityList;
    private final DBHolder dbHolder;
    private String username;

    public RecyclerAdapterServiceAdmin(List<ServiceEntity> serviceEntityList, DBHolder dbHolder) {
        this.serviceEntityList = serviceEntityList;
        this.dbHolder = dbHolder;
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
        String status;

        holder.bind(serviceEntity);

        getUsername(serviceEntity.getIdUser(), holder.binding.txtName);

        if (serviceEntity.getStatus() == 0) {
            status = holder.binding.getRoot().getResources().getString(R.string.belum_diservis);
        } else if (serviceEntity.getStatus() == 1) {
            status = holder.binding.getRoot().getResources().getString(R.string.sedang_diservis);
        } else {
            status = holder.binding.getRoot().getResources().getString(R.string.selesai_service);
        }

        holder.binding.txtStatus.setText(status);

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(holder.binding.getRoot().getContext(), DetailServiceActivity.class);
            i.putExtra("idService", serviceEntity.getIdService());
            i.putExtra("username", username);
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

    private void getUsername(int userId, TextView txtName) {
        class GetUsername extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                return dbHolder.getAppDB().userDao().getUsername(userId);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                username = s;
                txtName.setText(username);
            }
        }

        new GetUsername().execute();
    }
}
