package com.fourdsight.service.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fourdsight.service.model.ScrennShotAkkaModel;
import com.fourdsight.service.util.JsonUtil;

import akka.actor.ActorRef;

@Component
public class RabbitConsumer {
	private static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);
	@Autowired
	@Qualifier("handlingSupervisorBean")
	private ActorRef screenDataHandlingSupervisor;

	@RabbitListener(queues = "screen-data")
	public void recievedMessage(Message msg) {
		
		
		ScrennShotAkkaModel data = (ScrennShotAkkaModel) JsonUtil.jsonToObject(new String(msg.getBody()),
				ScrennShotAkkaModel.class);

		for (String url : data.getSiteUrlList()) {
			screenDataHandlingSupervisor.tell(url, ActorRef.noSender());
		}
		

	}

}
