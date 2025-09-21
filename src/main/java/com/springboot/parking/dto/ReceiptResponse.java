package com.springboot.parking.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class ReceiptResponse {
    private Long paymentId;
    private BigDecimal amount;
    private Instant timestamp;

    public ReceiptResponse(Long paymentId, BigDecimal amount, Instant timestamp) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public Long getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
