package com.projet.training.controller;

import java.io.InvalidObjectException;
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

import com.projet.training.entities.LoginEntity;
import com.projet.training.repository.LoginRepository;
import com.projet.training.services.LoginService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private LoginService ls;
	
	@GetMapping(value="/user" , consumes = "application/json")
	@CrossOrigin(origins="http://localhost:4200")
	public List<LoginEntity> listUser() {
	    return lr.findAll();
	}

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity<LoginEntity> get(@PathVariable int id) {
        	LoginEntity l = ls.findUser(id);
            return ResponseEntity.ok(l);
    }
	
    @PostMapping(value="/user" , consumes = "application/json")
    public ResponseEntity<LoginEntity> add( @RequestBody LoginEntity l ){
        try{
            ls.addUser( l );
            
            return ResponseEntity.ok().body(l);

        }catch ( InvalidObjectException e ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @PutMapping(value="/user/{id}" , consumes = "application/json")
    public void update( @PathVariable int id , @RequestBody LoginEntity l ) throws InvalidObjectException{
        try {
            ls.editUser( id , l );
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
