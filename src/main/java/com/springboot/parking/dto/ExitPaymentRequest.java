package com.springboot.parking.dto;

public class ExitPaymentRequest {
    private String paymentMethod; // e.g., CARD, CASH, UPI

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
