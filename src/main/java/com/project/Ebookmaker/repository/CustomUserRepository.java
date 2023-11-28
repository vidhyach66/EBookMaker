package com.project.Ebookmaker.repository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.Ebookmaker.entity.Users;

public interface CustomUserRepository {
	
	public Users findUser(String username);
	public Boolean findByUserName(String username);
}
