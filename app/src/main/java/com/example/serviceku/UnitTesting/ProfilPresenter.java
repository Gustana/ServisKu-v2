package com.example.serviceku.UnitTesting;

import com.example.serviceku.remote.model.auth.register.Register;

public class ProfilPresenter {
    private ProfilView view;
    private ProfilService service;
    private ProfilCallback callback;
    private String email, password, name, phoneNo, gender;

    public ProfilPresenter(ProfilView view, ProfilService service) {
        this.view = view;
        this.service = service;
    }

    public void onProfilClicked() {
        String regex = "[1-9]+";
        String regex1 = "[a-zA-Z]";

        if (view.getEmail().isEmpty()) {
            view.showEmailError("Email tidak boleh kosong");
            return;
        }
        else if (view.getPassword().isEmpty()) {
            view.showPasswordError("Password tidak boleh kosong");
            return;
        }
        else if(view.getNama().isEmpty()){
            view.showNamaError("Nama tidak boleh kosong");
            return;
        }
        else if(view.getPhoneNo().isEmpty()){
            view.showPhoneNoError("Nomor handphone tidak boleh kosong");
            return;
        }
        else if(view.getGender().equals("Gender kosong")){
            view.showGenderError("Jenis kelamin tidak boleh kosong");
            return;
        }
        else {
            service.profil(view, email, password, name, phoneNo, gender, new ProfilCallback() {
                @Override
                public void onSuccess(boolean value, String email, String password, String name, String phoneNo, String gender) {
                    view.startMainProfil();
                }

                @Override
                public void onError() {
                }
            });
            return;
        }
    }
}