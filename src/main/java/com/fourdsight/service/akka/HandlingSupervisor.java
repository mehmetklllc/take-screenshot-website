package com.fourdsight.service.akka;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fourdsight.service.config.PropertiesConfig;
import com.fourdsight.service.entity.ScreenshotWebsite;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.Routee;
import akka.routing.Router;
import akka.routing.SmallestMailboxRoutingLogic;

@Component(value = "handlingSupervisor")
@Scope("prototype")
public class HandlingSupervisor extends UntypedActor {

	private static final Logger logger = LoggerFactory.getLogger(HandlingSupervisor.class);

	
	private Router data4ScrennShotHandlingActor;

	@Autowired
	private SpringExtension springExtension;

	@Autowired
	PropertiesConfig pc;

	@Override
	public void preStart() throws Exception {

		logger.info("Akka Number of workers : " + pc.getAkkaWorkerNumber());
		data4ScrennShotHandlingActor(pc);
		super.preStart();

	}


	private void data4ScrennShotHandlingActor(PropertiesConfig pc) {

		List<Routee> routees = new ArrayList<Routee>();
		for (int i = 0; i < Integer.parseInt(pc.getAkkaWorkerNumber()); i++) {
			ActorRef r = getContext().actorOf(springExtension.props("data4ScrennShotHandlingActor"));
			getContext().watch(r);
			routees.add(new ActorRefRoutee(r));
		}
		data4ScrennShotHandlingActor = new Router(new SmallestMailboxRoutingLogic(), routees);

	}

	@Override
	public void onReceive(Object msg) throws Exception {
		
			String model = (String) msg;

			data4ScrennShotHandlingActor.route(model, getSender());

		

	}

}
