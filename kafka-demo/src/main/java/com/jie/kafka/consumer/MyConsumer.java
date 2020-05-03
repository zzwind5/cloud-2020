package com.jie.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {

	public static void main(String[] args) {

		Properties properties = new Properties();

		properties.put("bootstrap.servers", "192.168.222.20:9092,192.168.222.30:9092,192.168.222.40:9092");
		properties.put("group.id", "consumer_zhang");
		properties.put("enable.auto.commit", false);
		properties.put("auto.commit.interval.ms", 1000);
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("auto.offset.reset", "earliest");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Arrays.asList("first", "second"));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);

			records.forEach(item -> {
				System.out.println(item.topic() + "\t" + item.partition() + "\t" + item.value());
			});
		}
	}
}
