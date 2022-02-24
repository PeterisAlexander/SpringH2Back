package com.projet.training.controller;

import java.io.InvalidObjectException;
import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public Iterable<LoginEntity> listUsers() {
	    return lr.findAll();
	}

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public ResponseEntity<LoginEntity> get(@PathVariable int id) {
        try{
        	LoginEntity l = ls.findUser(id);
            return ResponseEntity.ok(l);
        }catch ( Exception e ){
            return ResponseEntity.notFound().build();
        }
    }
	
    @PostMapping(value="" , consumes = "application/json")
    public ResponseEntity<LoginEntity> add( @RequestBody LoginEntity l ){
        System.out.println( l );
        try{
            ls.addUser( l );

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand( l.getId() ).toUri();

            return ResponseEntity.created( uri ).body(l);

        }catch ( InvalidObjectException e ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @PutMapping(value="/{id}" , consumes = "application/json")
    public void update( @PathVariable int id , @RequestBody LoginEntity l ){
        try{
            ls.editUser( id , l );

        }catch ( NoSuchElementException e ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "User introuvable" );

        }catch ( InvalidObjectException e ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , e.getMessage() );
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        try{
            LoginEntity l = ls.findUser(id);
        }catch( Exception e ){
            return ResponseEntity.notFound().build();
        }

        try{
            ls.delete(id);
            return ResponseEntity.ok(null);
        }catch( Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }
}
