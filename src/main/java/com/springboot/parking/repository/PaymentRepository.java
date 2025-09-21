package com.springboot.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.parking.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
