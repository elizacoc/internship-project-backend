package com.kronsoft.project.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.kronsoft.project.dto.UserDto;
import com.kronsoft.project.validators.EmailConstraint;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	@NotNull(message = "Id cannot be null!")
	private Long id;
	
	@Column(name = "email", unique = true, nullable = false)
	@EmailConstraint
	@NotBlank(message = "Email cannot be blank!")
	private String email;
	
	@Column(name = "username", nullable = false, unique = true, length = 20)
	@NotBlank(message = "Username cannot be blank!")
	@Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters!")
	private String username;
	
	@Column(name = "password", nullable = false)
	@NotBlank(message = "Password cannot be blank!")
	private String password;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = Shape.STRING)
	@ApiModelProperty(required = true, example = "2021-08-20 05:07:30")
	@Column(name = "creation_date", nullable = false)
	@NotNull(message = "Creation date cannot be null!")
	private LocalDateTime creationDate;
	
	public UserEntity() {
		
	}
	
	public UserEntity(UserDto user) {
		BeanUtils.copyProperties(user, this);
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
