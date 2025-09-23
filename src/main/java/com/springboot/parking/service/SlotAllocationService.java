package com.springboot.parking.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.springboot.parking.domain.ParkingSlot;
import com.springboot.parking.domain.Ticket;
import com.springboot.parking.domain.Vehicle;
import com.springboot.parking.domain.VehicleType;
import com.springboot.parking.repository.ParkingSlotRepository;
import com.springboot.parking.repository.TicketRepository;
import com.springboot.parking.repository.VehicleRepository;

import jakarta.transaction.Transactional;

@Service
public class SlotAllocationService {

    private final ParkingSlotRepository slotRepo;
    private final TicketRepository ticketRepo;
    private final VehicleRepository vehicleRepo;

    public SlotAllocationService(ParkingSlotRepository slotRepo,
                                 TicketRepository ticketRepo,
                                 VehicleRepository vehicleRepo) {
        this.slotRepo = slotRepo;
        this.ticketRepo = ticketRepo;
        this.vehicleRepo = vehicleRepo;
    }

    
    @Transactional
    public Ticket allocateSlotAndCreateTicket(String plateNo, VehicleType vehicleType) {
        // Check if vehicle already exists; if not, save it
        Vehicle vehicle = vehicleRepo.findByPlateNo(plateNo)
                .orElseGet(() -> {
                    Vehicle v = new Vehicle();
                    v.setPlateNo(plateNo);
                    v.setType(vehicleType);
                    return vehicleRepo.save(v);
                });

        // Prevent duplicate active ticket for same vehicle
        if (ticketRepo.existsByVehicle_PlateNoAndStatus(plateNo, Ticket.Status.ACTIVE)) {
            throw new IllegalStateException("Vehicle already has an active ticket.");
        }

        // Lock and fetch available slot
        ParkingSlot slot = slotRepo.findFirstAvailableForTypeForUpdate(vehicleType)
                .orElseThrow(() -> new IllegalStateException("No available slots for type: " + vehicleType));

        slot.setStatus(ParkingSlot.Status.OCCUPIED);
        slotRepo.save(slot);

        // Create ticket
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setSlot(slot);
        ticket.setEntryTime(Instant.now());
        ticket.setStatus(Ticket.Status.ACTIVE);

        return ticketRepo.save(ticket);
    }
}
