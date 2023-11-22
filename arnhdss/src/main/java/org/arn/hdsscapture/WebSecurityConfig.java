package org.arn.hdsscapture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
//				.antMatchers("/controls/**").hasAnyRole("CONTROLLER","MANAGER")// can only setup
//				.antMatchers("/pi/**").hasRole("INVESTIGATOR")// can only enter data
//				.antMatchers("/pi/**").hasAnyRole("INVESTIGATOR","CONTROLLER")// can only enter data
//				.antMatchers("/**").hasRole("ADMINISTRATOR")// can view audits
                .anyRequest().authenticated()).formLogin(login -> login
                		
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll())
        		.logout(logout -> logout
        		.logoutUrl("/logout") // specifies the logout URL
        		.logoutSuccessUrl("/login?logout")
        		.permitAll());

        http.csrf(withDefaults());
        http.csrf().disable();
        http.headers(headers -> headers.frameOptions().disable());

      return http.build();

	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsServiceImpl userDetailService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailService)
				.passwordEncoder(bCryptPasswordEncoder).and().build();
	}

	@Bean
	WebSecurityCustomizer ignoringCustomizer() {
		return (web) -> web.ignoring().antMatchers("/h2-console/**", "/img/**", "/webjars/**", "/api/**");
	}

}