package com.projet.training.services;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.training.dto.UserDto;
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
	
	public Iterable<LoginEntity> saveAll(ArrayList<LoginEntity> loginList) {
		return lr.saveAll(loginList);
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
            lExistant.setFirstname( l.getFirstname() );
            lExistant.setLastname( l.getLastname() );
            lExistant.setBirthdate( l.getBirthdate() );
     
            lr.save( lExistant );

        }catch ( NoSuchElementException e ){
            throw e;
        }
    }

	public UserDto saveLogin(LoginEntity login) {
		login = lr.save(login);
		
		return UserDto.of(login);
		
	}
	
	public LoginEntity fetchLoginByUsername(String username) {
		return lr.findByUsername(username);
	}
	
}
