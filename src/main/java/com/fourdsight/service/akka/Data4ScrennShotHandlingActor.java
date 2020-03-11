package com.fourdsight.service.akka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fourdsight.service.entity.ScreenshotWebsite;
import com.fourdsight.service.screeen.ScreenShoter;
import com.fourdsight.service.services.ScreenshotWebsiteService;

import akka.actor.UntypedActor;

@Component(value = "data4ScrennShotHandlingActor")
@Scope("prototype")
public class Data4ScrennShotHandlingActor extends UntypedActor {

	private static final Logger logger = LoggerFactory.getLogger(Data4ScrennShotHandlingActor.class);

	@Autowired
	ScreenshotWebsiteService screenshotWebsiteService;

	@Override
	public void onReceive(Object msg) throws Exception {
		ScreenshotWebsite screenshotWebsite = null;
		String imageBase64 = null;
		ScreenShoter shoter = null;
		shoter = new ScreenShoter();
		screenshotWebsite = new ScreenshotWebsite();
		String url = (String) msg;
		imageBase64 = shoter.execute(url);

		screenshotWebsite.setSiteUrl(url.substring(7));
		screenshotWebsite.setSiteDescription(url + " desc");
		screenshotWebsite.setSiteImage(imageBase64);
		screenshotWebsiteService.createSite(screenshotWebsite);
		screenshotWebsite = null;
		logger.info("Site Screen Shot created : " + url);

	}

}
