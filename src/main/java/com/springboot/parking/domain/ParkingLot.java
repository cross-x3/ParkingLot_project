package com.springboot.parking.domain;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parking_lot")
@Getter
@Setter
public class ParkingLot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Floor>floors;
	private String name;
	private String location;
}
