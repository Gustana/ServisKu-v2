package com.example.serviceku.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.serviceku.room.dao.ServiceDao;
import com.example.serviceku.room.dao.UserDao;
import com.example.serviceku.room.entity.ServiceEntity;
import com.example.serviceku.room.entity.UserEntity;

@Database(entities = {UserEntity.class, ServiceEntity.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ServiceDao serviceDao();
}
