package com.jie.cloud.controller;

import com.jie.cloud.entities.CommonResult;
import com.jie.cloud.entities.Payment;
import com.jie.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/payment")
	public CommonResult<Integer> create(@RequestBody Payment payment) {
		int count = paymentService.create(payment);
		return count > 0 ?
				new CommonResult<>(200, "插入成功", count) :
				new CommonResult<>(500, "插入失败");
	}

	@GetMapping("/payment/{id}")
	public CommonResult<Payment> getPayment(@PathVariable ("id") Long id) {
		Payment payment = paymentService.getPayment(id);
		return payment != null ?
				new CommonResult<>(200, "成功", payment) :
				new CommonResult<>(500, "失败");
	}
}
