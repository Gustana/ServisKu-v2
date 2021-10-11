package com.example.serviceku.room;

import android.content.Context;

import androidx.room.Room;

public class DBHolder {
    public static final String DB_NAME = "ServiceKUdb";
    private static DBHolder dbHolder;

    private final AppDB appDB;

    public DBHolder(Context context){
        appDB = Room
                .databaseBuilder(context, AppDB.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public static synchronized DBHolder getInstance(Context context){
        if(dbHolder==null){
            dbHolder = new DBHolder(context);
        }

        return dbHolder;
    }

    public AppDB getAppDB(){
        return appDB;
    }
}
