package org.arn.hdsscapture.service;

import org.arn.hdsscapture.Dto.UserRegistrationDto;
import org.arn.hdsscapture.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

	User update(User user);
}
