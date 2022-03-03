package com.projet.training.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.training.dto.LoginDto;
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
	public ResponseEntity<String> login(@RequestBody LoginDto login) throws Exception {
		String username = login.getUsername();
		String password = login.getPassword();
		
		if((username == null || password == null) 
				|| (username == "" || password == "")) {
			throw new Exception("Username and Password are empty");
		}
		
		UserEntity loginObj = ls.fetchLoginByUsername(username);
		
		if(!password.equals(loginObj.getPassword()) 
				|| ObjectUtils.allNull(loginObj)) {
			return ResponseEntity.ok().body("Username or Password is incorrect");
		}

		return ResponseEntity.ok().body("Successfully login");
	}
	
	@PostMapping(value="/register")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<String> register(@RequestBody RegisterDto register) throws Exception {
		
		if(StringUtils.isAnyEmpty(register.getUsername(), 
				register.getPassword(), 
				register.getFirstname(), 
				register.getLastname()) 
				|| register.getBirthdate() == null ) {	
			throw new Exception("Bad credentials");
		}
		
		UserEntity loginObj = ls.fetchLoginByUsername(register.getUsername());
		
		if(register.getUsername().equals(loginObj.getUsername())) {
			return ResponseEntity.ok().body("User " + register.getUsername() +" is already exist");
		}
		if(!(register.getUsername().equals(register.getPassword()))) {
			return ResponseEntity.ok().body("Password must be the same as the username");
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
