package com.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ashokit.constant.KafkaConstant;
import com.ashokit.model.Customer;

@Service
public class CustomerService {
	@Autowired
	private KafkaTemplate<String, Customer> kafkaTemplate;
	
	public String saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		kafkaTemplate.send(KafkaConstant.TOPIC,customer);
		System.out.println("************Msg published to Kafka topic***************");
		return "Customer Data Stored in the Kafka Topic";
	}
	
	@KafkaListener(topics = KafkaConstant.TOPIC, groupId = KafkaConstant.GROUP_ID)
	public Customer listener(Customer c) {
		System.out.println("***Msg recieved from Kafka Topic ::" + c);
		return c;
	}


}
