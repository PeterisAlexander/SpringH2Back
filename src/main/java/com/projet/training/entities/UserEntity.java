package com.projet.training.entities;

import java.time.LocalDate;

import javax.persistence.*;

import com.projet.training.dto.RegisterDto;
import com.projet.training.dto.UpdateDto;
import com.projet.training.dto.UserDto;

@Entity
@Table(name="login", schema="", catalog="")
public class UserEntity {
	@Id
    @Column(name = "id", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column()
	private String username;
	
	@Column()
	private String password;
	
	@Column()
	private String lastname;
	
	@Column()
	private String firstname;
	
	@Column()
	private LocalDate birthdate;
	
	
	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param lastname
	 * @param firstname
	 * @param birthdate
	 */
	public UserEntity(String username, String password, String lastname, String firstname, LocalDate birthdate) {
		super();
		this.username = username;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.birthdate = birthdate;
	}

	public UserEntity() {}

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

	/**
	 * 
	 * @param l
	 * @return
	 */
	public static UserEntity of(RegisterDto l) {
		return new UserEntity(l.getUsername(), 
				l.getPassword(), 
				l.getLastname(), 
				l.getFirstname(), 
				l.getBirthdate());
	}
	
	
	/**
	 */
	public static UserEntity of(UpdateDto l) {
		return new UserEntity(l.getUsername(), 
				l.getPassword(), 
				l.getLastname(), 
				l.getFirstname(), 
				l.getBirthdate());
	}
}
