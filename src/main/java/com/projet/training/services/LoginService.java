package com.projet.training.services;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.training.dto.RegisterDto;
import com.projet.training.dto.UserDto;
import com.projet.training.entities.UserEntity;
import com.projet.training.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository lr;
	
	public Iterable<UserEntity> findAll() { 
		return lr.findAll();
	}
	
	public UserEntity findUser(int id) {
		return lr.findById(id).get();
	}
	
	public Iterable<UserEntity> saveAll(ArrayList<UserEntity> loginList) {
		return lr.saveAll(loginList);
	}
	
	public void addUser( UserEntity l ) throws InvalidObjectException {
        lr.save(l);
    }

    public void delete(int id) {
        lr.deleteById(id);
    }

    public void editUser( int id , UserEntity l) throws InvalidObjectException , NoSuchElementException {
        try{
            UserEntity lExistant =  lr.findById(id).get();

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

	public UserEntity register(RegisterDto register) {
		UserEntity login = new UserEntity(register.getUsername(),
				register.getPassword(),
				register.getLastname(),
				register.getFirstname(),
				register.getBirthdate()
		);
		
		return lr.save(login);
	}
	
	public UserEntity fetchLoginByUsername(String username) {
		return lr.findByUsername(username);
	}
	
}
