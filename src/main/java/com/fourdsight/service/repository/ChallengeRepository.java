package com.fourdsight.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fourdsight.service.entity.ScreenshotWebsite;

@Repository
public interface ChallengeRepository
		extends JpaRepository<ScreenshotWebsite, Long>, JpaSpecificationExecutor<ScreenshotWebsite> {

	@Query("SELECT s FROM ScreenshotWebsite s WHERE s.siteUrl=:siteUrl and s.deleted=:deleted")
	ScreenshotWebsite findBySiteName(@Param("siteUrl") String siteUrl, @Param("deleted") boolean deleted);

}
