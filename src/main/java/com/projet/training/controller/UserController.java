package com.projet.training.controller;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projet.training.dto.UserDto;
import com.projet.training.entities.UserEntity;
import com.projet.training.repository.LoginRepository;
import com.projet.training.services.LoginService;

@RestController
@RequestMapping("/crud")
public class UserController {
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private LoginService ls;
	
	@GetMapping(value="/user" , consumes = "application/json")
	@CrossOrigin(origins="http://localhost:4200")
	public List<UserDto> listUser() {
		ArrayList<UserEntity> login = (ArrayList<UserEntity>) lr.findAll();
		return UserDto.of(login);
	}

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity<UserDto> get(@PathVariable int id) {
        	UserEntity l = ls.findUser(id);
        	
            return ResponseEntity.ok(UserDto.of(l));
    }
	
    @PostMapping(value="/user" , consumes = "application/json")
    @CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<UserDto> add( @RequestBody UserEntity l ){
        try{
            ls.addUser( l );
            
            return ResponseEntity.ok().body(UserDto.of(l));

        }catch ( InvalidObjectException e ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @PutMapping(value="/user/{id}" , consumes = "application/json")
    public ResponseEntity<String> update( @PathVariable int id , @RequestBody UserEntity l ) throws InvalidObjectException{
        try {
            ls.editUser( id , l );
            return ResponseEntity.ok().body("Successfully update");
        }
        catch( NoSuchElementException e ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "User introuvable" );
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
            ls.delete(id);
            
            return ResponseEntity.ok("Successfully deleted");
    }
}
