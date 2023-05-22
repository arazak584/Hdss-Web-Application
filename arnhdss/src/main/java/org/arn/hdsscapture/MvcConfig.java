package org.arn.hdsscapture;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/userguide").setViewName("userguide");
		
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        
        registry.addViewController("/fw").setViewName("utility/fw_list");
        registry.addViewController("/zip").setViewName("utility/task");
        registry.addViewController("/round").setViewName("utility/round_list");
        registry.addViewController("/ed").setViewName("ed-home");
        registry.addViewController("/rp").setViewName("report");
        registry.addViewController("/utility").setViewName("ut-home");
        registry.addViewController("/user").setViewName("utility/user-list");
        
        registry.addViewController("/controls").setViewName("controls/dashboard");
        
 	}

}