package com.fourdsight.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fourdsight.service.constant.ChallengeConstant;


@Component
public class PropertiesConfig {

	@Value(ChallengeConstant.AKKA_WORKER_NUMBER)
	private String akkaWorkerNumber;

	public String getAkkaWorkerNumber() {
		return akkaWorkerNumber;
	}

	

}
