package com.example.serviceku.remote.model.profile.profileDetail;

import com.google.gson.annotations.SerializedName;

public class GetProfileItem {

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("nama")
    private String nama;

    @SerializedName("pass")
    private String pass;

    @SerializedName("level_user")
    private String levelUser;

    @SerializedName("status_email")
    private String statusEmail;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

    @SerializedName("email")
    private String email;

    public GetProfileItem(String noHp, String nama, String levelUser, String idUser, String jenisKelamin, String email) {
        this.noHp = noHp;
        this.nama = nama;
        this.pass = pass;
        this.levelUser = levelUser;
        this.statusEmail = statusEmail;
        this.idUser = idUser;
        this.jenisKelamin = jenisKelamin;
        this.email = email;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getNama() {
        return nama;
    }

    public String getPass() {
        return pass;
    }

    public String getLevelUser() {
        return levelUser;
    }

    public String getStatusEmail() {
        return statusEmail;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "no_hp = '" + noHp + '\'' +
                        ",nama = '" + nama + '\'' +
                        ",pass = '" + pass + '\'' +
                        ",level = '" + levelUser + '\'' +
                        ",status_email = '" + statusEmail + '\'' +
                        ",id_user = '" + idUser + '\'' +
                        ",jenis_kelamin = '" + jenisKelamin + '\'' +
                        ",email = '" + email + '\'' +
                        "}";
    }

}