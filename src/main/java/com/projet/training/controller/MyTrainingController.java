package com.projet.training.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.projet.training.entities.LoginEntity;
import com.projet.training.repository.LoginRepository;
import com.projet.training.services.LoginService;

@RestController
@RequestMapping("/api")
public class MyTrainingController {
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private LoginService ls;
	
	@PostMapping(value="/login")
	@CrossOrigin(origins="http://localhost:4200")
	public LoginEntity login(@RequestBody LoginEntity login) throws Exception {
		String username = login.getUsername();
		
		String password = login.getPassword();
		
		if((username == null && password == null) || (username == "" && password == "")) {
			throw new Exception("Username and Password are empty");
		}
		
		LoginEntity loginObj = ls.fetchLoginByUsernameAndPassword(username, password);
		if(loginObj == null) {
			throw new Exception("Bad credentials");
		}
		
		return loginObj;
	}
	
	@GetMapping("/users")
	@CrossOrigin(origins="http://localhost:4200")
	public Iterable<LoginEntity> listUsers() {
	    return lr.findAll();
	}
	
	@PostMapping(value="/createUser")
	@CrossOrigin(origins="http://localhost:4200")
	public LoginEntity loginOrRegister(@RequestBody LoginEntity login) throws Exception {
		String username = login.getUsername();
		
		if(username == null && login.getUsername() == "") {
			throw new Exception("User " + username +" is already exist");
		}
		
		LoginEntity loginObj = ls.fetchLoginByUsername(username);
		
		loginObj = ls.saveLogin(login);
		
		if(loginObj == null) {
			throw new Exception("Bad credentials");		
		}
		
		return loginObj;
	}
	
	@GetMapping("/generator")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<ArrayList<LoginEntity>> randomPerson(@RequestParam("nbPerson") Integer nbPerson) {
		
		LoginEntity login = new LoginEntity();
		
		ArrayList<LoginEntity> loginList = new ArrayList<>();	
		
		Faker faker = new Faker();
		
		for(int i = 0; i< nbPerson; i++) {
			String username = faker.ancient().hero();
			login.setId(i);
			login.setUsername(username);
			login.setPassword(username);
			login.setLastname(faker.name().lastName());
			login.setFirstname(faker.name().firstName());
			login.setBirthdate(faker.date().birthday());
						
			loginList.add(new LoginEntity(
					login.getId(),
					login.getUsername(),
					login.getPassword(),
					login.getLastname(),
					login.getFirstname(),
					login.getBirthdate()
				)
			);
		}
		
		return ResponseEntity.ok().body(loginList);
	    
	}

}
