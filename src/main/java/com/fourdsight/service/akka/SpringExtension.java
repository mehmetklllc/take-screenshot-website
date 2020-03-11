package com.fourdsight.service.akka;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import akka.actor.Extension;
import akka.actor.Props;

@Component
public class SpringExtension implements Extension {

	private ApplicationContext applicationContext;

	public void initialize(ApplicationContext _applicationContext) {

		this.applicationContext = _applicationContext;
	}

	public Props props(String _actorBeanName) {

		return Props.create(SpringActorProducer.class, applicationContext, _actorBeanName);
	}
}