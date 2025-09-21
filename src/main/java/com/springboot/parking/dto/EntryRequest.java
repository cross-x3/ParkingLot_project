package com.springboot.parking.dto;

import com.springboot.parking.domain.VehicleType;

public class EntryRequest {
    private String plateNo;
    private VehicleType vehicleType;
    private String entryGate; // optional metadata

    public String getPlateNo() {
        return plateNo;
    }
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getEntryGate() {
        return entryGate;
    }
    public void setEntryGate(String entryGate) {
        this.entryGate = entryGate;
    }
}
