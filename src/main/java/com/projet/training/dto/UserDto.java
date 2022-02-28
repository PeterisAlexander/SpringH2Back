package com.projet.training.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.projet.training.entities.LoginEntity;

public class UserDto {
	private int userid;
	
	private String username;

	private String lastname;
	
	private String firstname;
	
	private LocalDate birthdate;
	
	
	/**
	 * 
	 * @param userid
	 * @param lastname
	 * @param firstname
	 * @param birthdate
	 */
	public static UserDto of(LoginEntity login) {
		return new UserDto(login.getId(),
				login.getUsername(),
				login.getLastname(),
				login.getFirstname(),
				login.getBirthdate());
	}
	
	
	public static List<UserDto> of(ArrayList<LoginEntity> loginList){
		return loginList.stream()
				.map(UserDto::of)
				.toList();
	}
	
	/**
	 * 
	 * @param userid
	 * @param lastname
	 * @param firstname
	 * @param birthdate
	 */
	public UserDto(int userid,
			String username,
			String lastname,
			String firstname,
			LocalDate birthdate) {
		super();
		this.userid = userid;
		this.username = username;
		this.lastname = lastname;
		this.firstname = firstname;
		this.birthdate = birthdate;
	}

	public UserDto() {
		
	}
	

	/**
	 * @return the userid
	 */
	public int getId() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setId(int userid) {
		this.userid = userid;
	}
	

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the birthdate
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	
	
}
