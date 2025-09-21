package com.springboot.parking.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.parking.domain.Ticket;
import com.springboot.parking.repository.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepo;

    public TicketService(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public Optional<Ticket> findById(Long ticketId) {
        return ticketRepo.findById(ticketId);
    }

    public Ticket save(Ticket ticket) {
        return ticketRepo.save(ticket);
    }
}
