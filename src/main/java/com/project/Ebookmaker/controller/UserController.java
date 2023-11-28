package com.project.Ebookmaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Ebookmaker.entity.Users;
import com.project.Ebookmaker.service.UserService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/users/")
public class UserController {
     
	
	@Autowired
	UserService userService;
	
	@GetMapping("/allUsers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Users> allUsers(){
		List<Users>users=userService.getAllUsers();
		return users;
	}
	
	@GetMapping("/user/{id}")
	@PreAuthorize("hasRole('ROLE_AUTHOR')")
	public ResponseEntity<Users> getUser(@PathVariable("id") Long id) {
		Users user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
}
