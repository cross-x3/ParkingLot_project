package com.springboot.parking.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Floor {

	@Id
	private int id;
	private int floorNumber;

	@OneToMany
	private List<ParkingSlot> slots;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

}
