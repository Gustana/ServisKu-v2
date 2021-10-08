package com.example.serviceku.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {

    @PrimaryKey
    public int idUser;

    public String username;
    public String password;

}
