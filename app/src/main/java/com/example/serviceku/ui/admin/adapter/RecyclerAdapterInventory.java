package com.example.serviceku.ui.admin.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceku.BR;
import com.example.serviceku.R;
import com.example.serviceku.databinding.RecyclerItemInventoryBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.inventory.DeleteInventoryResponse;
import com.example.serviceku.remote.model.inventory.inventoryList.GetInventoryListItem;
import com.example.serviceku.ui.admin.DetailInventoryActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAdapterInventory extends RecyclerView.Adapter<RecyclerAdapterInventory.UI> {

    private final String TAG = RecyclerAdapterInventory.class.getSimpleName();

    private List<GetInventoryListItem> inventoryList;

    private ApiClient apiClient;

    public RecyclerAdapterInventory(List<GetInventoryListItem> inventoryList) {
        this.inventoryList = inventoryList;
    }

    @NonNull
    @Override
    public UI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemInventoryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_item_inventory,
                parent,
                false
        );

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        return new UI(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UI holder, int position) {
        GetInventoryListItem inventoryData = inventoryList.get(position);

        holder.bind(inventoryData);

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(holder.itemView.getContext(), DetailInventoryActivity.class);
            i.putExtra("idInventory", inventoryData.getIdInventori());
            holder.itemView.getContext().startActivity(i);
        });

        holder.itemView.setOnLongClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(holder.itemView.getContext());
            alertDialog.setMessage("Yakin Hapus?");
            alertDialog.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                apiClient.deleteInventory(Integer.parseInt(inventoryData.getIdInventori())).enqueue(new Callback<DeleteInventoryResponse>() {
                    @Override
                    public void onResponse(Call<DeleteInventoryResponse> call, Response<DeleteInventoryResponse> response) {
                        Log.i(TAG, "onResponse: delete: " + response.body().getCode());
                        if (response.isSuccessful()) {
                            Toast.makeText(holder.itemView.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteInventoryResponse> call, Throwable t) {
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
        return inventoryList.size();
    }

    public static class UI extends RecyclerView.ViewHolder {
        private final RecyclerItemInventoryBinding binding;

        public UI(@NonNull RecyclerItemInventoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Object obj) {
            binding.setVariable(BR.inventoryData, obj);
            binding.executePendingBindings();
        }
    }
}
