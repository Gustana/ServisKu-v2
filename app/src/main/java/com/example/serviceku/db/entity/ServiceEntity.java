package com.example.serviceku.db.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ServiceEntity {
    @PrimaryKey
    public  int idService;

    public String idUser;
    public String noPlat;
    public String problems;
    public  String date;
    public String phoneNo;
    public float totalPrice;
    public int status;
}
