package com.example.serviceku.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.serviceku.MainActivity;
import com.example.serviceku.R;
import com.example.serviceku.databinding.ActivityHomeUserBinding;
import com.example.serviceku.helper.SPManager;
import com.example.serviceku.ui.aboutUs.AboutUsActivity;
import com.example.serviceku.ui.user.fragment.ProfileFragment;
import com.example.serviceku.ui.user.fragment.UserServiceFragment;
import com.example.serviceku.util.LogoutUtil;

public class HomeUserActivity extends AppCompatActivity implements LogoutUtil {

    private ActivityHomeUserBinding binding;

    private SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        spManager = new SPManager(this);

        setNavigation(new UserServiceFragment());

        binding.navigationUser.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            if (item.getItemId() == R.id.menuUserService) {
                fragment = new UserServiceFragment();
            } else if (item.getItemId() == R.id.menuProfile) {
                fragment = new ProfileFragment();
            }

            return setNavigation(fragment);
        });
    }

    private boolean setNavigation(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(binding.menuContainer.getId(), fragment);
            fragmentTransaction.commit();

            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuLogout) {
            logout();
            return true;
        } else if (item.getItemId() == R.id.menuAboutUs) {
            startActivity(new Intent(this, AboutUsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void logout() {
        spManager.clearSP();
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}