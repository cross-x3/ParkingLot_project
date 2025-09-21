package com.springboot.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.parking.domain.ParkingSlot;
import com.springboot.parking.domain.VehicleType;

import jakarta.persistence.LockModeType;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

    // Find nearest available slot for a vehicle type (ordered by floor and slot number)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM ParkingSlot s " +
           "WHERE s.type = :type AND s.status = 'AVAILABLE' " +
           "ORDER BY s.floor.floorNumber ASC, s.slotNumber ASC")
    Optional<ParkingSlot> findFirstAvailableForTypeForUpdate(@Param("type") VehicleType type);
}
