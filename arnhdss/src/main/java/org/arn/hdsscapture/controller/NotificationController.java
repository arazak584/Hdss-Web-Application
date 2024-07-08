package org.arn.hdsscapture.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.arn.hdsscapture.entity.Fieldworker;
import org.arn.hdsscapture.entity.UserTable;
import org.arn.hdsscapture.repository.FieldworkerRepository;
import org.arn.hdsscapture.repository.UserTableRepository;
import org.arn.hdsscapture.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
	private UserTableRepository userRepo;
	
	@Autowired
	FieldworkerRepository frepo;
	
    private static final Logger logger = Logger.getLogger(NotificationController.class.getName());
	
	@Scheduled(cron = "0 0 9 * * *") // execute at 09:00 AM every day
    //@Scheduled(cron = "0 15 20 * * *") // execute at 08:15 PM every day
    public void sendEmail() {
        List<UserTable> users = userRepo.ActiveUsers();
        List<Fieldworker> fws = frepo.lastSync();

        if (!fws.isEmpty()) {
            String subject = "FIELDWORKER DATA SYNC";

            for (UserTable user : users) {
                String userFullName = user.getUser_fname() + " " + user.getUser_lname();
                StringBuilder text = new StringBuilder(String.format("Dear %s,\n\nThe following fieldworkers have not synced their data for the past 3+ days:\n", userFullName));

                for (Fieldworker fw : fws) {
                    text.append(String.format("\n- %s %s, Last Sync: %s", fw.getFirstName(), fw.getLastName(), fw.getInsertDate()));
                }

                text.append("\n\nPlease ensure they sync their data as soon as possible.\n\nBest regards,\nHDSS CAPTURE");

                try {
                    emailService.sendSimpleMessage(user.getUser_email(), subject, text.toString());
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Failed to send email to " + user.getUser_email(), e);
                }
            }
        }
    }
}