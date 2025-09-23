package com.springboot.parking.dto;

import java.time.Instant;

public class EntryResponse {
    private Long ticketId;
    private int slotNumber;
    private Instant entryTime;

    public EntryResponse(Long ticketId, int slotNumber, Instant entryTime) {
        this.ticketId = ticketId;
        this.slotNumber = slotNumber;
        this.entryTime = entryTime;
    }

    public Long getTicketId() {
        return ticketId;
    }
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public int getSlotNumber() {
        return slotNumber;
    }
    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Instant getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(Instant entryTime) {
        this.entryTime = entryTime;
    }
}