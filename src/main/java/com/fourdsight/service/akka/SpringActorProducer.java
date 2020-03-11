package com.fourdsight.service.akka;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

public class SpringActorProducer implements IndirectActorProducer {

	private final ApplicationContext applicationContext;
	private final String actorBeanName;

	public SpringActorProducer(ApplicationContext _applicationContext, String _actorBeanName) {

		this.applicationContext = _applicationContext;
		this.actorBeanName = _actorBeanName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Actor> actorClass() {

		return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
	}

	@Override
	public Actor produce() {

		return (Actor) applicationContext.getBean(actorBeanName);
	}
}