package com.jie.cloud.service.impl;

import com.jie.cloud.dao.PaymentDao;
import com.jie.cloud.entities.Payment;
import com.jie.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Override
	public int create(Payment payment) {
		return paymentDao.create(payment);
	}

	@Override
	public Payment getPayment(Long id) {
		return paymentDao.getPaymentById(id);
	}
}
