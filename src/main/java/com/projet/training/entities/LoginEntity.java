package com.projet.training.entities;

import javax.persistence.*;
import com.projet.training.dto.UserDto;

@Entity
@Table(name="login", schema="", catalog="")
public class LoginEntity {
	@Id
    @Column(name = "id", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "username")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@ManyToOne
    @JoinColumn(name = "userid")
	private UserDto user;
	

	/**
	 * @param login
	 */
	public LoginEntity(int id, String username, String password, UserDto user) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.user = user;
	}
	
	
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param lastname
	 * @param firstname
	 * @param birthdate
	 */
	public LoginEntity(String username, String password, UserDto user) {
		super();
		this.username = username;
		this.password = password;
		this.user = user;
	}

	
	public LoginEntity() {}

	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 * @return 
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
	
	/**
	 * @return the loginDto
	 */
	public UserDto getLogin() {
		return user;
	}

	/**
	 * @param loginDto the loginDto to set
	 */
	public void setLogin(UserDto user) {
		this.user = user;
	}
	
}
