package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.model.Payment;
import com.emlakcepte.service.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	@GetMapping
	public ResponseEntity<List<Payment>> getAll() {
		return ResponseEntity.ok(paymentService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<Payment> create(@RequestBody Payment payment){
		return ResponseEntity.ok(paymentService.create(payment));
	}
	
}
