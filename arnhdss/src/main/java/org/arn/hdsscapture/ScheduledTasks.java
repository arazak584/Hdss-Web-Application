package org.arn.hdsscapture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	
	@Autowired
    private ConfigurableApplicationContext context;
	
	//@Scheduled(cron = "0 31 8 * * *")
    @Scheduled(cron = "0 0 4 * * *") // This cron expression triggers the method at 4 AM every day
	public void restartApplication() {
        // Add logic to restart the application
        System.out.println("Restarting the application at 4:00 AM");

        // Perform application restart
        Thread thread = new Thread(() -> {
            context.close();
            SpringApplication.run(ArnhdssApplication.class);
        });

        thread.setDaemon(false);
        thread.start();
    }

}
