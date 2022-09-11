package com.kronsoft.project.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.kronsoft.project.entities.UserEntity;
import com.kronsoft.project.validators.PasswordConstraint;
import com.kronsoft.project.validators.PasswordSizeConstraint;

public class UserDto {
	private Long id;
	
	private String email;
	
	private String username;
	
	@PasswordSizeConstraint
	@PasswordConstraint
	private String password;
	
	private LocalDateTime creationDate;
	
	public UserDto() {
		
	}
	
	public UserDto(UserEntity user) {
		BeanUtils.copyProperties(user, this, password);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
}
