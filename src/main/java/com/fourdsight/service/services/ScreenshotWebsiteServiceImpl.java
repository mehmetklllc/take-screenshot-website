package com.fourdsight.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourdsight.service.entity.ScreenshotWebsite;
import com.fourdsight.service.repository.ChallengeRepository;

@Service
public class ScreenshotWebsiteServiceImpl implements ScreenshotWebsiteService {

	@Autowired
	ChallengeRepository challengeRepository;

	@Override
	public ScreenshotWebsite createSite(ScreenshotWebsite screenshotWebsite) {

		return challengeRepository.save(screenshotWebsite);
	}

	@Override
	public ScreenshotWebsite updateSite(ScreenshotWebsite screenshotWebsite) {

		return challengeRepository.save(screenshotWebsite);
	}

	@Override
	public ScreenshotWebsite getSiteByUrl(String url, boolean deleted) {

		return challengeRepository.findBySiteName(url, deleted);
	}

}
