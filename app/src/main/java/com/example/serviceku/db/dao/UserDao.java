package com.example.serviceku.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.serviceku.db.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void insertUser(UserEntity userEntity);

    @Query("Select * FROM UserEntity")
    public List<UserEntity> userList();

    @Query("SELECT * FROM userentity WHERE username = :username")
    public UserEntity getUser(String username);

    @Query("SELECT idUser FROM UserEntity WHERE username = :username")
    public int getIdUser(String username);

    @Query("SELECT username FROM UserEntity WHERE idUser = :idUser")
    public String getUsername(int idUser);

}
