package com.jie.cloud.service;

import com.jie.cloud.entities.Payment;

public interface PaymentService {

	int create(Payment payment);

	Payment getPayment(Long id);
}
