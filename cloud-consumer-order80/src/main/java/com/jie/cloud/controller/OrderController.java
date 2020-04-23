package com.jie.cloud.controller;

import com.jie.cloud.entities.CommonResult;
import com.jie.cloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class OrderController {

	private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/payment/create")
	public CommonResult<Integer> create(Payment payment) {
		return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommonResult.class);
	}

	@GetMapping("/payment/{id}")
	public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
		return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
	}
}
