package com.jie.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducterCallback {

	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.put("bootstrap.servers", "192.168.222.20:9092,192.168.222.30:9092,192.168.222.40:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//		properties.put("partitioner.class", "com.jie.kafka.partioner.MyPartioner");

		try (KafkaProducer producer = new KafkaProducer<>(properties)) {

			for (int i = 0; i < 100; i++) {
				try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
				producer.send(new ProducerRecord("second", "demo_" + i),
						(recordMetadata, e) -> {
							if (e != null) {
								e.printStackTrace();
								return;
							}

							System.out.println(recordMetadata.partition() + "__" + recordMetadata.offset());
						});
			}
			producer.close();
		}
	}
}
