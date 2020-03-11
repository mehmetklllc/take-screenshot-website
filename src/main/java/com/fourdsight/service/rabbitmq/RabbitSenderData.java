package com.fourdsight.service.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fourdsight.service.config.PropertiesConfig;

@Service
public class RabbitSenderData {

	private static final Logger logger = LoggerFactory.getLogger(RabbitSenderData.class);

	@Value("screen-data")
	String queueName;

	@Value("screen")
	private String exchange;

	@Value("challenge-service")
	private String routingkey;

	@Autowired
	PropertiesConfig pc;

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void dataPublish(Object screen) {
		logger.info("dataPublish method called..");
		rabbitTemplate.convertAndSend(exchange, routingkey, screen);

	}
}
