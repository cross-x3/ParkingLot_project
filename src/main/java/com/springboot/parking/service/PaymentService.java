package com.springboot.parking.service;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.springboot.parking.domain.ParkingSlot;
import com.springboot.parking.domain.Payment;
import com.springboot.parking.domain.PricingRule;
import com.springboot.parking.domain.Ticket;
import com.springboot.parking.repository.ParkingSlotRepository;
import com.springboot.parking.repository.PaymentRepository;
import com.springboot.parking.repository.PricingRuleRepository;
import com.springboot.parking.repository.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

    private final TicketRepository ticketRepo;
    private final ParkingSlotRepository slotRepo;
    private final PaymentRepository paymentRepo;
    private final PricingRuleRepository pricingRepo;

    public PaymentService(TicketRepository ticketRepo,
                          ParkingSlotRepository slotRepo,
                          PaymentRepository paymentRepo,
                          PricingRuleRepository pricingRepo) {
        this.ticketRepo = ticketRepo;
        this.slotRepo = slotRepo;
        this.paymentRepo = paymentRepo;
        this.pricingRepo = pricingRepo;
    }

    /**
     * Process payment and free the parking slot.
     */
    @Transactional
    public Payment processExitAndPayment(Long ticketId, String paymentMethod) {
        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ticket ID"));

        if (ticket.getStatus() != Ticket.Status.ACTIVE) {
            throw new IllegalStateException("Ticket is not active.");
        }

        Instant now = Instant.now();
        long hours = java.time.Duration.between(ticket.getEntryTime(), now).toHours();
        if (hours == 0) {
			hours = 1; // Minimum 1 hour
		}

        PricingRule rule = pricingRepo.findByVehicleType(ticket.getVehicle().getType())
                .orElseThrow(() -> new IllegalStateException("No pricing rule configured"));

        BigDecimal amount = rule.calculateAmount(hours);

        // Simulate external payment
        if (!simulatePayment(amount, paymentMethod)) {
            throw new RuntimeException("Payment failed.");
        }

        // Save Payment
        Payment payment = new Payment();
        payment.setTicket(ticket);
        payment.setAmount(amount);
        payment.setMethod(paymentMethod);
        payment.setStatus(Payment.Status.SUCCESS);
        payment.setTimestamp(now);
        paymentRepo.save(payment);

        // Update ticket
        ticket.setExitTime(now);
        ticket.setStatus(Ticket.Status.PAID);
        ticketRepo.save(ticket);

        // Free slot
        ParkingSlot slot = ticket.getSlot();
        slot.setStatus(ParkingSlot.Status.AVAILABLE);
        slotRepo.save(slot);

        return payment;
    }

    private boolean simulatePayment(BigDecimal amount, String method) {
        // Simulate always-success for demo
        return true;
    }
}
