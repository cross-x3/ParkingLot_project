package com.springboot.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.parking.domain.PricingRule;
import com.springboot.parking.domain.VehicleType;

@Repository
public interface PricingRuleRepository extends JpaRepository<PricingRule, Long> {
    Optional<PricingRule> findByVehicleType(VehicleType vehicleType);
}