package com.kronsoft.project.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.kronsoft.project.entities.UserEntity;
import com.kronsoft.project.validators.PasswordConstraint;
import com.kronsoft.project.validators.PasswordSizeConstraint;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {
	private Long id;
	
	private String email;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	@PasswordSizeConstraint
	@PasswordConstraint
	private String password;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	@ApiModelProperty(required = true, example = "2021-08-20 05:07:30")
	private LocalDateTime creationDate;
	
	public UserDto() {
		
	}
	
	public UserDto(UserEntity user) {
		BeanUtils.copyProperties(user, this, "password");
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
