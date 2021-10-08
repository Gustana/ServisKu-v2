package com.example.serviceku.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.serviceku.db.dao.ServiceDao;
import com.example.serviceku.db.dao.UserDao;
import com.example.serviceku.db.entity.ServiceEntity;
import com.example.serviceku.db.entity.UserEntity;

@Database(entities = {UserEntity.class, ServiceEntity.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ServiceDao serviceDao();
}
