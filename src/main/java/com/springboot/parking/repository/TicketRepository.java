package com.springboot.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.parking.domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByVehicle_PlateNoAndStatus(String plateNo, Ticket.Status status);
}
