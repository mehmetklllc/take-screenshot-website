package com.fourdsight.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fourdsight.service.akka.SpringExtension;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@Configuration
public class AkkaConfig {
	// The application context is needed to initialize the Akka Spring Extension
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private SpringExtension springExtension;

	@Bean
	public ActorSystem actorSystem() {

		ActorSystem system = ActorSystem.create("challenge-service", akkaConfiguration());

		// Initialize the application context in the Akka Spring Extension
		springExtension.initialize(applicationContext);
		return system;
	}

	@Bean
	public ActorRef handlingSupervisorBean() {

		return actorSystem().actorOf(springExtension.props("handlingSupervisor"));
	}

	/**
	 * Read configuration from application.conf file
	 */
	@Bean
	public Config akkaConfiguration() {

		return ConfigFactory.load();
	}

}
