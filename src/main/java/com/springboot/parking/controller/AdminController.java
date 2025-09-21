package com.springboot.parking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.parking.domain.ParkingSlot;
import com.springboot.parking.domain.PricingRule;
import com.springboot.parking.repository.ParkingSlotRepository;
import com.springboot.parking.repository.PricingRuleRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ParkingSlotRepository slotRepo;
    private final PricingRuleRepository pricingRepo;

    public AdminController(ParkingSlotRepository slotRepo, PricingRuleRepository pricingRepo) {
        this.slotRepo = slotRepo;
        this.pricingRepo = pricingRepo;
    }

    /**
     * Add a new parking slot.
     */
    @PostMapping("/slots")
    public ResponseEntity<ParkingSlot> addSlot(@RequestBody ParkingSlot slot) {
        return ResponseEntity.ok(slotRepo.save(slot));
    }

    /**
     * Add or update a pricing rule.
     */
    @PostMapping("/pricing")
    public ResponseEntity<PricingRule> addPricing(@RequestBody PricingRule rule) {
        return ResponseEntity.ok(pricingRepo.save(rule));
    }
}
