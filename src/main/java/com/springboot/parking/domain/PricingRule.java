package com.springboot.parking.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pricing_rule")
public class PricingRule {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private int freeHours;
    private BigDecimal hourlyRate;

    public BigDecimal calculateAmount(long totalHours) {
        long billable = Math.max(0L, totalHours - freeHours);
        return hourlyRate.multiply(BigDecimal.valueOf(billable));
    }
}
