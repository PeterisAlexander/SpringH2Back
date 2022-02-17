package com.projet.training.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.training.entities.LoginEntity;
import com.projet.training.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository lr;
	
	public LoginEntity saveLogin(LoginEntity login) {
		return lr.save(login);
	}
	
	public LoginEntity fetchLoginByUsername(String username) {
		return lr.findByUsername(username);
	}
	
	public LoginEntity fetchLoginByUsernameAndPassword(String username, String password) {
		return lr.findByUsernameAndPassword(username, password);
	}
	
}
