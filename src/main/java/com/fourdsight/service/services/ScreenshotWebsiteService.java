package com.fourdsight.service.services;

import com.fourdsight.service.entity.ScreenshotWebsite;

public interface ScreenshotWebsiteService {
	
	public ScreenshotWebsite createSite(ScreenshotWebsite screenshotWebsite);

	public ScreenshotWebsite updateSite(ScreenshotWebsite screenshotWebsite);

	public ScreenshotWebsite getSiteByUrl(String url, boolean deleted);
}
