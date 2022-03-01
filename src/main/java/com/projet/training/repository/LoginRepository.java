package com.projet.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.training.entities.UserEntity;

public interface LoginRepository extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByUsername(String username);
	public UserEntity findByUsernameAndPassword(String username, String password);
}