package com.DigitalBooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DigitalBooks.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
