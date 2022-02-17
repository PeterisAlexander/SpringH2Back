package com.projet.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.training.entities.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
	public LoginEntity findByUsername(String username);
	public LoginEntity findByUsernameAndPassword(String username, String password);
}