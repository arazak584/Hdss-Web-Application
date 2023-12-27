package org.arn.hdsscapture;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class CookieConfig {
	
	@Value("${server.servlet.session.cookie.name}")
	private String sessionCookieName;

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("server-" + sessionCookieName);
        // Other cookie properties can be set here if needed
        return serializer;
    }

    @Bean
    public String uniqueApplicationId() {
        // Generate a random UUID as the unique application ID
        return UUID.randomUUID().toString();
    }

}
