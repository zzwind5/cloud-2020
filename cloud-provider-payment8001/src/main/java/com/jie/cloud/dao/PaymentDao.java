package com.jie.cloud.dao;

import com.jie.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

	int create(Payment payment);

	Payment getPaymentById(@Param("id") Long id);
}
