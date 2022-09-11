package com.kronsoft.project.services;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kronsoft.project.dao.UserRepository;
import com.kronsoft.project.dto.UserDto;
import com.kronsoft.project.entities.UserEntity;
import com.kronsoft.project.exceptions.EmailAlreadyExistsException;
import com.kronsoft.project.exceptions.UsernameAlreadyExistsException;

@Service
public class UserService {
	
	private static final Logger Logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDto registerUser(UserDto userDto) throws Exception {
		final String email = userDto.getEmail();
		if(userRepository.existsByEmail(email)) {
			throw new EmailAlreadyExistsException(email);
		}
		final String username = userDto.getUsername();
		if(userRepository.existsByUsername(username)) {
			throw new UsernameAlreadyExistsException(username);
		}
		Logger.info("Register user with email: {}", email);
		String rawPassword = userDto.getPassword();
		userDto.setPassword(passwordEncoder.encode(rawPassword));
		userDto.setCreationDate(LocalDateTime.now());
		UserEntity user = new UserEntity(userDto);
		return new UserDto(userRepository.save(user));
	}
	
	public UserDto userToPersist(UserDto userDto) {
		UserEntity user = new UserEntity(userDto);
		return new UserDto(userRepository.save(user));
	}
		
}
