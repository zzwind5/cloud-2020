package com.jie.kafka.partioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class MyPartioner implements Partitioner {

	@Override
	public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
		return 1;
	}

	@Override
	public void close() {

	}

	@Override
	public void configure(Map<String, ?> map) {

	}
}
