package com.example.serviceku.UnitTesting;

public interface ProfilView {

    String getEmail();
    void showEmailError(String message);

    String getPassword();
    void showPasswordError(String message);

    String getNama();
    void showNamaError(String message);

    String getPhoneNo();
    void showPhoneNoError(String message);

    String getGender();
    void showGenderError(String message);

    void startMainProfil();

    void showProfilError(String message);

    void showErrorResponse(String message);
}