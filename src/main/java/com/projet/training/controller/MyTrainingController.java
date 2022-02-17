package com.projet.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	public MyTrainingController(LoginRepository loginRepo) {
		this.lr = loginRepo;
	}
	
	@GetMapping("/users")
	public Iterable<LoginEntity> listUsers() {
	    return lr.findAll();
	}
	
	@PostMapping(value="/loginUsername")
	@CrossOrigin(origins="http://localhost:4200")
	public LoginEntity loginOrRegister(@RequestBody LoginEntity login) throws Exception {
		String username = login.getUsername();
		if(username != null && login.getUsername() != "") {
			LoginEntity loginObj = ls.fetchLoginByUsername(username);
			if(loginObj != null) {
				throw new Exception("User " + username +" is already exist");
			} else {
				loginObj = ls.saveLogin(login);
				return loginObj;
			}
		}
		
		LoginEntity loginObj;
		loginObj = ls.saveLogin(login);
		return loginObj;
	}
	
	@PostMapping(value="/login")
	@CrossOrigin(origins="http://localhost:4200")
	public LoginEntity login(@RequestBody LoginEntity login) throws Exception {
		String username = login.getUsername();
		String password = login.getPassword();
		if((username != null && password != null) || (username != "" && password != "")) {
			LoginEntity loginObj = ls.fetchLoginByUsernameAndPassword(username, password);
			if(loginObj != null) {
				throw new Exception("Bad credentials");
			}
		}
		LoginEntity loginObj;
		loginObj = ls.fetchLoginByUsernameAndPassword(username, password);
		return loginObj;
	}

}
