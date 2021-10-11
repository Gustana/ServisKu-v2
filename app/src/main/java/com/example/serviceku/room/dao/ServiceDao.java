package com.example.serviceku.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.serviceku.room.entity.ServiceEntity;

import java.util.List;

@Dao
public interface ServiceDao {

    @Insert
    public void insertService(ServiceEntity serviceEntity);

    @Query("SELECT * FROM ServiceEntity " +
            "INNER JOIN UserEntity ON ServiceEntity.idUser = UserEntity.idUser " +
            "WHERE ServiceEntity.idUser = :idUser")
    public List<ServiceEntity> getUserServiceList(int idUser);

    @Query("SELECT * FROM ServiceEntity " +
            "INNER JOIN UserEntity ON ServiceEntity.idUser = UserEntity.idUser")
    public List<ServiceEntity> getAdminServiceList();


    @Query("SELECT * FROM ServiceEntity " +
            "INNER JOIN UserEntity ON ServiceEntity.idUser = UserEntity.idUser " +
            "WHERE ServiceEntity.idService = :idService")
    public ServiceEntity getServiceDetail(int idService);


    //this method only to update status & totalPrice
    @Update
    public void updateService(ServiceEntity serviceEntity);

}
