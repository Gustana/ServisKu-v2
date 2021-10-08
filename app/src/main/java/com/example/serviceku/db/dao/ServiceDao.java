package com.example.serviceku.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.serviceku.db.entity.ServiceEntity;

import java.util.List;

@Dao
public interface ServiceDao {

    @Insert
    public void insertService(ServiceEntity serviceEntity);

    @Query("SELECT * FROM ServiceEntity INNER JOIN UserEntity ON ServiceEntity.idUser = UserEntity.idUser")
    public List<ServiceEntity> getService();

    //this method only to update status & totalPrice
    @Update
    public void updateService(ServiceEntity serviceEntity);

}
