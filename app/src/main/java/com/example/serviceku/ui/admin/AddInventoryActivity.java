package com.example.serviceku.ui.admin;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.serviceku.R;
import com.example.serviceku.databinding.ActivityAddInventoryBinding;
import com.example.serviceku.remote.ApiClient;
import com.example.serviceku.remote.ApiInstance;
import com.example.serviceku.remote.model.inventory.InsertInventoryResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddInventoryActivity extends AppCompatActivity {

    private static final String TAG = AddInventoryActivity.class.getSimpleName();
    private static final int GALLERY_REQUEST = 111;
    private static final int CAMERA_REQUEST = 112;
    private static final int CAMERA_PERMISSION_CODE = 100;

    private ActivityAddInventoryBinding binding;

    private ApiClient apiClient;

    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddInventoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiClient = ApiInstance.getRetrofitInstance().create(ApiClient.class);

        binding.ivGambar.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddInventoryActivity.this);
            alertDialog.setMessage("Pilih Gambar Dari");
            alertDialog.setPositiveButton(getResources().getString(R.string.camera), (dialogInterface, i) -> {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            });
            alertDialog.setNegativeButton(getResources().getString(R.string.gallery), (dialogInterface, i) -> {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            });

            alertDialog.show();
        });

        binding.btnAddInventory.setOnClickListener(v -> {
            String inventoryName = binding.edtInventoryName.getText().toString();
            int inventoryAmount = Integer.parseInt(binding.edtInventoryAmount.getText().toString());
            String sparepartType = binding.edtInventoryType.getText().toString();
            float inventoryPrice = Float.parseFloat(binding.edtInventoryPrice.getText().toString());

            String selectedImage = "";

            if (bitmap != null) {
                selectedImage = getEncodedImage(bitmap);
            }

            apiClient.insertInventory(
                    inventoryName,
                    inventoryAmount,
                    sparepartType,
                    inventoryPrice,
                    selectedImage
            ).enqueue(new Callback<InsertInventoryResponse>() {
                @Override
                public void onResponse(Call<InsertInventoryResponse> call, Response<InsertInventoryResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(AddInventoryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<InsertInventoryResponse> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t.getCause());
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
        });
    }

    private String getEncodedImage(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, outputStream);

        byte[] bytes = outputStream.toByteArray();

        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(AddInventoryActivity.this, "Camera permission granted", Toast.LENGTH_SHORT).show();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(AddInventoryActivity.this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {
                try {
                    Uri selectedImage = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    binding.ivGambar.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (requestCode == CAMERA_REQUEST) {
                bitmap = (Bitmap) data.getExtras().get("data");
                binding.ivGambar.setImageBitmap(bitmap);
            }
        }
    }
}