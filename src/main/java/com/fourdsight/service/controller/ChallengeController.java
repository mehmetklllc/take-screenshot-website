package com.fourdsight.service.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fourdsight.service.entity.ScreenshotWebsite;
import com.fourdsight.service.model.ScrennShotAkkaModel;
import com.fourdsight.service.rabbitmq.RabbitSenderData;
import com.fourdsight.service.services.ScreenshotWebsiteService;
import com.fourdsight.service.util.JsonUtil;

@RestController
public class ChallengeController {
	private static final Logger logger = LoggerFactory.getLogger(ChallengeController.class);

	@Autowired
	private RabbitSenderData senderData;

	@Autowired
	ScreenshotWebsiteService screenshotWebsiteService;

	@RequestMapping(value = "/create-site-screenshot", method = RequestMethod.POST)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	public ResponseEntity<ScrennShotAkkaModel> createScreenShot(@RequestBody ScrennShotAkkaModel model) {
		

		ResponseEntity<ScrennShotAkkaModel> response = null;

		try {
			/// rabbit sender
			senderData.dataPublish(model);

			response = new ResponseEntity<ScrennShotAkkaModel>(model, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error", e);

		}
		return response;
	}

	@RequestMapping(value = "/by/{url}", method = RequestMethod.GET)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	public ResponseEntity<String> bySiteScreenShot(@PathVariable("url") String url) {
	

		ResponseEntity<String> response = null;

		try {
			ScreenshotWebsite screen = screenshotWebsiteService.getSiteByUrl(url, false);

			response = new ResponseEntity<String>(JsonUtil.objectToJson(screen), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error", e);

		}
		return response;
	}

	

}
