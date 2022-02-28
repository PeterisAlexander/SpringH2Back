package com.projet.training.services;

import java.io.InvalidObjectException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.training.entities.LoginEntity;
import com.projet.training.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository lr;
	
	public Iterable<LoginEntity> findAll() { 
		return lr.findAll();
	}
	
	public LoginEntity findUser(int id) {
		return lr.findById(id).get();
	}
	
	public void addUser( LoginEntity l ) throws InvalidObjectException {
        lr.save(l);
    }

    public void delete(int id) {
        lr.deleteById(id);
    }

    public void editUser( int id , LoginEntity l) throws InvalidObjectException , NoSuchElementException {
        try{
            LoginEntity lExistant =  lr.findById(id).get();

            lExistant.setUsername( l.getUsername() );
            lExistant.setPassword( l.getPassword() );
            lExistant.setLogin( l.getLogin() );
            lr.save( lExistant );

        }catch ( NoSuchElementException e ){
            throw e;
        }
    }

	public LoginEntity saveLogin(LoginEntity login) {
		return lr.save(login);
	}
	
	public LoginEntity fetchLoginByUsername(String username) {
		return lr.findByUsername(username);
	}
	
}
