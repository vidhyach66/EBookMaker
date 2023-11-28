package com.project.Ebookmaker.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.project.Ebookmaker.jwt.MessageResponse;
import com.project.Ebookmaker.jwt.SignupRequest;
import com.project.Ebookmaker.entity.ERole;
import com.project.Ebookmaker.entity.Role;
import com.project.Ebookmaker.entity.Users;
import com.project.Ebookmaker.jwt.JwtRequest;
import com.project.Ebookmaker.jwt.JwtResponse;
import com.project.Ebookmaker.repository.RoleRepository;
import com.project.Ebookmaker.repository.UserRepository;
import com.project.Ebookmaker.service.UserDetailsImpl;

import com.project.Ebookmaker.utility.JwtUtility;


import org.springframework.security.authentication.AuthenticationManager;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	 Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRepository roleRepository;
	
	@Autowired
    private JwtUtility jwtUtility;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	
	
	@PostMapping("/Authenticate")
	public ResponseEntity<?> Authenticate(@Valid @RequestBody JwtRequest jwtRequest){
		 
		logger.info("inside authentication");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtility.generateJwtToken(authentication);
		System.out.println(jwt);
		
		
		 final UserDetailsImpl user =  (UserDetailsImpl) authentication.getPrincipal();
		 logger.info("reached here");
		 List<String> roles = user.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());
		 
		 return ResponseEntity.ok(new JwtResponse(jwt,user.getId(),user.getUsername(),roles)); 
	 }
	

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		if (userRepository.findByUserName(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's account
		Users users = new Users(signUpRequest.getUsername(), 
							 encoder.encode(signUpRequest.getPassword()),signUpRequest.getEmail());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			logger.info("reached here");
			Role userRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			
			roles.add(userRole);
			
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					logger.info("reached here");
					roles.add(adminRole);

					break;
				case "author":
					Role authorRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			         roles.add(authorRole);
			         logger.info("reached here");
			        break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_AUTHOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		users.setRoles(roles);
		userRepository.save(users);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	

}
