package com.arn.hdss.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.arn.hdss.Dto.UserRegistrationDto;
import com.arn.hdss.entity.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
