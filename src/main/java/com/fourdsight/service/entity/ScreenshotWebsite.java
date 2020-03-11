package com.fourdsight.service.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "screenshot_website", schema = "public")
public class ScreenshotWebsite extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public ScreenshotWebsite() {
		this.id = UUID.randomUUID().toString();
	}

	@Id
	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "site_url", nullable = false)
	@Size(max = 2555)
	private String siteUrl;

	@Column(name = "site_image")
	private String siteImage;

	@Column(name = "site_description", nullable = false)
	@Size(max = 255)
	private String siteDescription;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getSiteImage() {
		return siteImage;
	}

	public void setSiteImage(String siteImage) {
		this.siteImage = siteImage;
	}

	public String getSiteDescription() {
		return siteDescription;
	} 	

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

}
