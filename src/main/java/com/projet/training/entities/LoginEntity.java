package com.projet.training.entities;

import javax.persistence.*;

@Entity
@Table(name="login", schema="", catalog="")
public class LoginEntity {
	private int id;
	private String username;
	private String password;
	
	/**
	 * @param id
	 * @param username
	 * @param password
	 */
	public LoginEntity(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public LoginEntity() {}

	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
    @Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Basic
    @Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
