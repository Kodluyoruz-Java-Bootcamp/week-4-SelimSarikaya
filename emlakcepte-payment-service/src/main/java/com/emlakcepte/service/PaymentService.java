package com.emlakcepte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakcepte.model.Payment;
import com.emlakcepte.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	public Payment create(Payment payment) {

		payment.setDidPay(true);
		Payment createdPayment = paymentRepository.save(payment);
		System.out.println("Payment is done");
		return createdPayment;
	}

	public List<Payment> getAll() {
		return paymentRepository.findAll();
	}

}
