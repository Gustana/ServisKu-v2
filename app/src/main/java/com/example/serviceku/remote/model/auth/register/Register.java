package com.example.serviceku.remote.model.auth.register;


public class Register {
    private String email;
    private String password;
    private String nama;
    private String phoneNo;
    private String gender;

    public Register(String email, String password, String nama, String phoneNo, String gender) {
        this.email = email;
        this.password = password;
        this.nama = nama;
        this.phoneNo = phoneNo;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}