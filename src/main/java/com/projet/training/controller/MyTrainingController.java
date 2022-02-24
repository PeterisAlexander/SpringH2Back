package com.projet.training.controller;

import java.time.LocalDate;
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

import com.projet.training.entities.LoginEntity;
import com.projet.training.methodes.RandomInformation;
import com.projet.training.services.LoginService;

@RestController
@RequestMapping("/api")
public class MyTrainingController {
	
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
		
		LoginEntity loginObj = ls.fetchLoginByUsername(username);
		LoginEntity loginInfo = new LoginEntity(loginObj.getId(), loginObj.getUsername(), loginObj.getLastname(), loginObj.getFirstname(), loginObj.getBirthdate());
	
		
		return loginInfo;
	}
	
	@PostMapping(value="/createUser")
	@CrossOrigin(origins="http://localhost:4200")
	public LoginEntity loginOrRegister(@RequestBody LoginEntity login) throws Exception {
		
		if(login.getUsername() == null || login.getUsername() == "") {
			throw new Exception("User " + login.getUsername() +" is already exist");
		}
		
		LoginEntity loginObj = ls.fetchLoginByUsername(login.getUsername());
		
		loginObj = ls.saveLogin(login);
		
		if(loginObj == null) {
			throw new Exception("Bad credentials");		
		}
		
		return loginObj;
	}
	
	@GetMapping("/generator")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<ArrayList<LoginEntity>> randomPerson(@RequestParam("nbPerson") Integer nbPerson) {
		
		ArrayList<LoginEntity> loginList = new ArrayList<>();	
		ArrayList<LoginEntity> loginListInfo = new ArrayList<>();	
		
		LoginEntity login = new LoginEntity();
		
		for(int i = 0; i< nbPerson; i++) {
			String usernameAndPassword = RandomInformation.randomString();
			String firstname = RandomInformation.randomString();
			String lastname = RandomInformation.randomString();
			LocalDate birthdate = RandomInformation.randomDate();

			login.setId(i);
			login.setUsername(usernameAndPassword);
			login.setPassword(usernameAndPassword);
			login.setLastname(lastname);
			login.setFirstname(firstname);
			login.setBirthdate(birthdate);
			
			
			loginList.add(ls.saveLogin(new LoginEntity(
					i,
					usernameAndPassword.toLowerCase(),
					usernameAndPassword.toLowerCase(),
					lastname.toUpperCase(),
					firstname.toLowerCase(),
					birthdate
				))
			);
			
			loginListInfo.add(new LoginEntity(
					i,
					usernameAndPassword.toLowerCase(),
					lastname.toUpperCase(),
					firstname.toLowerCase(),
					birthdate
				)
			);
			
		}
		
		return ResponseEntity.ok().body(loginListInfo);
	}

}
