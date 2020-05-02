package com.jie.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducterMain {

	public static void main(String[] args) {

		try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }

		Properties properties = new Properties();

		properties.put("bootstrap.servers", "192.168.222.20:9092,192.168.222.30:9092,192.168.222.40:9092");
		properties.put("ack", "all");
		properties.put("retries", 3);
		properties.put("batch.size", 16384);
		properties.put("linger.ms", 1);
		properties.put("buffer.memory", 33554432);
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		try (KafkaProducer<Object, Object> kafka = new KafkaProducer<>(properties)) {
			for (int i = 0; i < 100; i++) {
				kafka.send(new ProducerRecord<>("first", i+"","zhang_" + i));
			}
		}
	}
}
