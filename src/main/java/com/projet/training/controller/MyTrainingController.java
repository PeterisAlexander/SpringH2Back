package com.projet.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.training.dto.RegisterDto;
import com.projet.training.dto.UserDto;
import com.projet.training.entities.UserEntity;
import com.projet.training.services.LoginService;
import com.projet.training.utils.RandomInformation;

@RestController
@RequestMapping("/api")
public class MyTrainingController {
	
	@Autowired
	private LoginService ls;
	
	@PostMapping(value="/login")
	@CrossOrigin(origins="http://localhost:4200")
	public UserDto login(@RequestBody UserEntity login) throws Exception {
		String username = login.getUsername();
		String password = login.getPassword();
		
		if((username == null || password == null) || (username == "" || password == "")) {
			throw new Exception("Username and Password are empty");
		}
		
		UserEntity loginObj = ls.fetchLoginByUsername(username);	
		
		return UserDto.of(loginObj);
	}
	
	@PostMapping(value="/register")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<String> register(@RequestBody RegisterDto register) throws Exception {
		
		if(register.getUsername() == null || register.getUsername().equals("")) {			
			throw new Exception("Bad credentials");
		}
		
		UserEntity loginObj = ls.fetchLoginByUsername(register.getUsername());
		
		if(loginObj != null) {
			throw new Exception("User " + register.getUsername() +" is already exist");
		}
		
		ls.register(register);
		
		return ResponseEntity.ok().body("User created");
	}
	
	@GetMapping("/generator")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<List<UserDto>> randomPerson(@RequestParam("nbPerson") Integer nbPerson) {
		
		ArrayList<UserEntity> loginList = new ArrayList<>();	
		
		for(int i = 0; i< nbPerson; i++) {
			String usernameAndPassword = RandomInformation.randomString();
			
			UserEntity loginEntity = new UserEntity(
					usernameAndPassword,
					usernameAndPassword,
					RandomInformation.randomString(),
					RandomInformation.randomString(),
					RandomInformation.randomDate()
			);
			
			loginList.add(loginEntity);
			
		}
		
		ls.saveAll(loginList);
		
		return ResponseEntity.ok().body(UserDto.of(loginList));
	}

}
