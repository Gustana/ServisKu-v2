package com.example.serviceku.UnitTesting;

import android.content.Context;
import android.content.Intent;

import com.example.serviceku.ui.auth.RegisterActivity;

public class ActivityUtil {

    private Context context;

    public ActivityUtil(Context context) {
        context = context;
    }

    public void startMainProfil() {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }
}