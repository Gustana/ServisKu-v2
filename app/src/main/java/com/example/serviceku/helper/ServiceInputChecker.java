package com.example.serviceku.helper;

public class ServiceInputChecker {
    private String noPlat, problem, vehicleType;

    public ServiceInputChecker(String noPlat, String problem, String vehicleType) {
        this.noPlat = noPlat;
        this.problem = problem;
        this.vehicleType = vehicleType;
    }

    public boolean isNoPlatEmpty() {
        return noPlat.isEmpty();
    }

    public boolean isProblemEmpty() {
        return problem.isEmpty();
    }

    public boolean isVehicleTypeEmpty() {
        return vehicleType.isEmpty();
    }

    public String getNoPlat() {
        return noPlat;
    }

    public String getProblem() {
        return problem;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getEmptyMessage() {
        return "Semua field harus diisi";
    }

}
