package com.projet.training.dto;

import com.projet.training.entities.UserEntity;

public class LoginDto {
	private int loginId;
	private String username;
	private String password;
	
	public static LoginDto of(UserEntity login) {
		return new LoginDto(
				login.getId(),
				login.getUsername(),
				login.getPassword()
		);
	}
	
	public LoginDto(int loginId, String username, String password) {
		super();
		this.loginId = loginId;
		this.username = username;
		this.password = password;
	}
	

	/**
	 * @return the loginId
	 */
	public int getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(int loginId) {
		this.loginId = loginId;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
