package com.ashokit.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ashokit.constant.KafkaConstant;
import com.ashokit.model.Customer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
	@Bean
	public ConsumerFactory<String, Customer> getConsumerFactory()
	{
		Map<String ,Object> configProps=new HashMap<String ,Object>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.HOST);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(Customer.class));
	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Customer> getConcurrentKafkaListenerContainerFactory()
	{
		ConcurrentKafkaListenerContainerFactory<String, Customer> concurrentKafkaListenerContainerFactory=new ConcurrentKafkaListenerContainerFactory<String, Customer>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(getConsumerFactory());
		return concurrentKafkaListenerContainerFactory;
	}

}