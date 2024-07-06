package org.arn.hdsscapture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class ArnhdssApplication {
	
//	@Autowired
//    private EmailService emailService;

	public static void main(String[] args) {
		SpringApplication.run(ArnhdssApplication.class, args);
	}
	
//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail() {
//		emailService.sendSimpleMessage("abdzakus@gmail.com",
//				"This is Test Message" ,"This the body component");
//	}

}
