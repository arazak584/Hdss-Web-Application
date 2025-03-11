package org.arn.hdsscapture.utils;

import javax.transaction.Transactional;

import org.arn.hdsscapture.repository.ResidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateRecords {
	
	
	@Autowired
	  ResidencyRepository repo;
	 
	 	// Schedule the task to run daily at midnight
	    //@Scheduled(cron = "0 0 0 * * ?")
	 	// Schedule the task to run daily at 12:00 AM and 12:00 PM
	    @Scheduled(cron = "0 0 0,12 * * ?")
	    @Transactional
	    public void scheduleResidencyUpdate() {

	    	repo.updateResidency();
	    	repo.updateInmigration();
	    	repo.updateOutmigration();
	    	System.out.print("Successful Update");
	    }

}
