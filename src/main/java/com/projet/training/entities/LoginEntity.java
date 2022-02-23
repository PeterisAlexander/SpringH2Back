package com.projet.training.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="login", schema="", catalog="")
public class LoginEntity {
	private int id;
	private String username;
	private String password;
	private String lastname;
	private String firstname;
	private LocalDate birthdate;
	
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param lastname
	 * @param firstname
	 * @param birthdate
	 */
	public LoginEntity(int id, String username, String password, String lastname, String firstname, LocalDate birthdate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.birthdate = birthdate;
	}
	

	public LoginEntity() {}

	/**
	 * @return the id
	 */
	@Id
    @Column(name = "id", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Basic
    @Column(name = "username")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Basic
    @Column(name = "password")
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
	 * @return the lastname
	 */
	@Basic
    @Column(name = "lastname")
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
	@Basic
    @Column(name = "firstname")
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
	@Basic
    @Column(name = "birthdate")
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * @param date the birthdate to set
	 */
	public void setBirthdate(LocalDate date) {
		this.birthdate = date;
	}
	
	@Override
	public String toString() {
		return "LoginEntity [id=" + id + ", username=" + username + ", password=" + password + ", lastname=" + lastname
				+ ", firstname=" + firstname + ", birthdate=" + birthdate + "]";
	}
	
	
}
