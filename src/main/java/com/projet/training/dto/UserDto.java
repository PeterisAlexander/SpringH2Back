package com.projet.training.dto;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user", schema="", catalog="")
public class UserDto {
	@Id
    @Column(name = "userid", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userid;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "birthdate")
	private LocalDate birthdate;
	
	
	/**
	 * 
	 * @param userid
	 * @param lastname
	 * @param firstname
	 * @param birthdate
	 */
	public static UserDto of(String lastname, 
			String firstname, 
			LocalDate birthdate) {
		return new UserDto(lastname, firstname, birthdate);
	}
	
	
	/**
	 * 
	 * @param userid
	 * @param lastname
	 * @param firstname
	 * @param birthdate
	 */
	public UserDto(String lastname, String firstname, LocalDate birthdate) {
		super();
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
