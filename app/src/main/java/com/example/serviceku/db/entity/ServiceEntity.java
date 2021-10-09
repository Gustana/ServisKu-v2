package com.example.serviceku.db.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ServiceEntity {
    @PrimaryKey(autoGenerate = true)
    private int idService;

    private int idUser;
    private String noPlat;
    private String problems;
    private String date;
    private String phoneNo;
    private float totalPrice;
    private String vehicleType;
    private int status;

    public ServiceEntity(int idUser, String noPlat, String problems, String date, String phoneNo, float totalPrice, String vehicleType, int status) {
        this.idUser = idUser;
        this.noPlat = noPlat;
        this.problems = problems;
        this.date = date;
        this.phoneNo = phoneNo;
        this.totalPrice = totalPrice;
        this.vehicleType = vehicleType;
        this.status = status;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNoPlat() {
        return noPlat;
    }

    public void setNoPlat(String noPlat) {
        this.noPlat = noPlat;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "idService=" + idService +
                ", idUser=" + idUser +
                ", noPlat='" + noPlat + '\'' +
                ", problems='" + problems + '\'' +
                ", date='" + date + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", totalPrice=" + totalPrice +
                ", vehicleType='" + vehicleType + '\'' +
                ", status=" + status +
                '}';
    }
}
