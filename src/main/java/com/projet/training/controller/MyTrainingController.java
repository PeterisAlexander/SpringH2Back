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

import com.projet.training.dto.UserDto;
import com.projet.training.entities.LoginEntity;
import com.projet.training.services.LoginService;
import com.projet.training.utils.RandomInformation;

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
		LoginEntity loginInfo = new LoginEntity(login.getId(), username, password, loginObj.getLogin());
	
		
		return loginInfo;
	}
	
	@PostMapping(value="/createUser")
	@CrossOrigin(origins="http://localhost:4200")
	public LoginEntity loginOrRegister(@RequestBody LoginEntity login) throws Exception {
		
		if(login.getUsername() == null || login.getUsername() == "") {
			throw new Exception("User " + login.getUsername() +" is already exist");
		}
		
		LoginEntity loginObj = ls.fetchLoginByUsername(login.getUsername());
		
		
		if(loginObj == null) {
			throw new Exception("Bad credentials");		
		}
		
		loginObj = ls.saveLogin(login);
		return loginObj;
	}
	
	@GetMapping("/generator")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<ArrayList<LoginEntity>> randomPerson(@RequestParam("nbPerson") Integer nbPerson) {
		
		ArrayList<LoginEntity> loginList = new ArrayList<>();	
//		ArrayList<LoginEntity> loginListInfo = new ArrayList<>();	
		
		LoginEntity login = new LoginEntity();
		UserDto user = new UserDto();
		
		
		
		for(int i = 0; i< nbPerson; i++) {
			String usernameAndPassword = RandomInformation.randomString();
			
			login.setUsername(usernameAndPassword);
			login.setPassword(usernameAndPassword);
			
			
			user.setLastname(RandomInformation.randomString());
			user.setFirstname(RandomInformation.randomString());
			user.setBirthdate(RandomInformation.randomDate());
			
			LoginEntity loginEntity = new LoginEntity(
					login.getUsername(),
					login.getPassword(),
					UserDto.of(user.getFirstname(), user.getLastname(), user.getBirthdate())
			);
			
			//ls.saveLogin(loginEntity);
			
			loginList.add(loginEntity);
			System.out.println(loginList);
			
		}
		
		return ResponseEntity.ok().body(loginList);
	}

}
