package org.arn.hdsscapture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArnhdssApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArnhdssApplication.class, args);
	}

} 
