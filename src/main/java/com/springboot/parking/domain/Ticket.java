package com.springboot.parking.domain;

import java.time.Instant;

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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ticket")
@Getter
@Setter
public class Ticket {
	public enum Status { ACTIVE, PAID, EXITED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id")
    private ParkingSlot slot;

    private Instant entryTime;
    private Instant exitTime;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ParkingSlot getSlot() {
		return slot;
	}

	public void setSlot(ParkingSlot slot) {
		this.slot = slot;
	}

	public Instant getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Instant entryTime) {
		this.entryTime = entryTime;
	}

	public Instant getExitTime() {
		return exitTime;
	}

	public void setExitTime(Instant exitTime) {
		this.exitTime = exitTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
