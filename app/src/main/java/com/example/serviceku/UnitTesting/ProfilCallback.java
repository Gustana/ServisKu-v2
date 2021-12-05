package com.example.serviceku.UnitTesting;

import com.example.serviceku.remote.model.auth.register.Register;

public interface ProfilCallback {

    void onSuccess(boolean value, String email, String password, String phoneNo, String name, String gender);

    void onError();
}