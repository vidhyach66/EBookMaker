package com.project.Ebookmaker.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.Ebookmaker.entity.Users;

@Service
public interface UserService{

	public List<Users> getAllUsers();
	public Users getUserById(Long id);
	
}
