package com.projet.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.projet.training.entities.LoginEntity;
import com.projet.training.repository.LoginRepository;

@Controller
public class MyTrainingController {
	
	@Autowired
	LoginRepository loginRepo;
	
	@PostMapping(value="login")
	public String login(Model model, LoginEntity loginEntity) {
		loginRepo.save(loginEntity);
		model.addAttribute("login", new LoginEntity());
		return "login";
	}
	
	@GetMapping(value="index")
	public String index(Model model, LoginEntity loginEntity) {
		model.addAttribute("login", new LoginEntity());
		model.addAttribute("logins", loginRepo.findAll());
		
		return "index";
	}
}
