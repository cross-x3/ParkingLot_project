package com.springboot.parking.domain;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parking_slot",
		uniqueConstraints = @UniqueConstraint(columnNames = {"floor_number", "slot_number", "parking_lot_id"}))
@Getter
@Setter
public class ParkingSlot implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//encapsulation
	public enum Status {AVAILABLE, OCCUPIED, RESERVED}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private VehicleType type;
	private int floorNumber;
	private int slotNumber;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;

	@Enumerated(EnumType.STRING)
	private Status status = Status.AVAILABLE;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parking_lot_id")
	private ParkingLot parkingLot;

}
