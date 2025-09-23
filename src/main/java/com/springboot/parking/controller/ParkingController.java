package com.springboot.parking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.parking.domain.Payment;
import com.springboot.parking.domain.Ticket;
import com.springboot.parking.dto.EntryRequest;
import com.springboot.parking.dto.EntryResponse;
import com.springboot.parking.dto.ExitPaymentRequest;
import com.springboot.parking.dto.ReceiptResponse;
import com.springboot.parking.service.PaymentService;
import com.springboot.parking.service.SlotAllocationService;
import com.springboot.parking.service.TicketService;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final SlotAllocationService allocationService;
    private final PaymentService paymentService;
    private final TicketService ticketService;

    public ParkingController(SlotAllocationService allocationService,
                             PaymentService paymentService,
                             TicketService ticketService) {
        this.allocationService = allocationService;
        this.paymentService = paymentService;
        this.ticketService = ticketService;
    }

    
    @PostMapping("/entry")
    public ResponseEntity<EntryResponse> entry(@RequestBody EntryRequest req) {
        Ticket ticket = allocationService.allocateSlotAndCreateTicket(req.getPlateNo(), req.getVehicleType());
        EntryResponse resp = new EntryResponse(ticket.getId(),
                                               ticket.getSlot().getSlotNumber(),
                                               ticket.getEntryTime());
        return ResponseEntity.ok(resp);
    }

    
    @PostMapping("/exit/{ticketId}/pay")
    public ResponseEntity<ReceiptResponse> payAndExit(@PathVariable Long ticketId,
                                                      @RequestBody ExitPaymentRequest req) {
        Payment payment = paymentService.processExitAndPayment(ticketId, req.getPaymentMethod());
        ReceiptResponse receipt = new ReceiptResponse(payment.getId(),
                                                      payment.getAmount(),
                                                      payment.getTimestamp());
        return ResponseEntity.ok(receipt);
    }

    
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long ticketId) {
        return ticketService.findById(ticketId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
