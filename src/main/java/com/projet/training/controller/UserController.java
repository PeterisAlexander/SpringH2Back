package com.projet.training.controller;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isAllEmpty;
import static org.apache.commons.lang3.StringUtils.isAnyEmpty;

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

import com.projet.training.dto.RegisterDto;
import com.projet.training.dto.UpdateDto;
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

	@GetMapping(value = "/user", consumes = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<UserDto> listUser() {
		return lr.findAll().stream().map(UserDto::of).collect(toList());
	}

	@GetMapping(value = "/user/{id}", produces = "application/json")
	public ResponseEntity<UserDto> get(@PathVariable Integer id) {

		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		UserEntity l = ls.findUser(id);

		return ResponseEntity.ok(UserDto.of(l));
	}

	@PostMapping(value = "/user", consumes = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String> add(@RequestBody RegisterDto l) {
		
		if(l == null 
				|| isAnyEmpty(l.getFirstname(), 
						l.getLastname(), 
						l.getPassword(), 
						l.getUsername())
				|| l.getBirthdate() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		try {
			ls.addUser(UserEntity.of(l));

			return ResponseEntity.ok().body("Register OK");

		} catch (InvalidObjectException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping(value = "/user/{id}", consumes = "application/json")
	public ResponseEntity<String> update(@PathVariable int id, @RequestBody UpdateDto l)
			throws InvalidObjectException {
		
		if(l == null 
				|| (isAllEmpty(l.getFirstname(), 
						l.getLastname(), 
						l.getPassword(), 
						l.getUsername())
						&& l.getBirthdate() == null)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		try {
			ls.editUser(id, UserEntity.of(l));
			return ResponseEntity.ok().body("Successfully update");
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User introuvable");
		}
	}

	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		ls.delete(id);

		return ResponseEntity.ok("Successfully deleted");
	}
}
