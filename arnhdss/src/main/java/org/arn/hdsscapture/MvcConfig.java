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
        
        registry.addViewController("/pi").setViewName("pi-home");
        registry.addViewController("/rv").setViewName("rv-home");
        registry.addViewController("/ed").setViewName("ed-home");
        
        registry.addViewController("/controls").setViewName("controls/dashboard");
        
 	}

}