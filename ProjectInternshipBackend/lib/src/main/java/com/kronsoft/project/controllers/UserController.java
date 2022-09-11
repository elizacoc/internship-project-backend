package com.kronsoft.project.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.project.dto.UserDto;
import com.kronsoft.project.exceptions.EmailAlreadyExistsException;
import com.kronsoft.project.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	public static Logger Logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDto registerUser(@Valid @RequestBody UserDto user) throws Exception {
		Logger.info("[REST] POST to /user/register");
		return userService.registerUser(user);
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDto updateUser(@RequestBody UserDto user) {
		return userService.userToPersist(user);
	}
}
