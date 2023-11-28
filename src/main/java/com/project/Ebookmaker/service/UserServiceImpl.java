package com.project.Ebookmaker.service;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Ebookmaker.entity.ERole;
import com.project.Ebookmaker.entity.Role;
import com.project.Ebookmaker.entity.Users;
import com.project.Ebookmaker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;



	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findUser(username);
		System.out.println(user.getPassword());
		UserDetailsImpl users = UserDetailsImpl.build(user);
		System.out.println(users.getPassword());
		return users;
	    
	}



	@Override
	public List<Users> getAllUsers(){
		List<Users> users = userRepository.findAll();
		List<Users> filteredUsers = new ArrayList<Users>();
		
		for(Users user:users){
			Set<Role> roles = user.getRoles();
			for(Role role:roles) {
				if(role.getName()==ERole.ROLE_AUTHOR) {
					filteredUsers.add(user);
				}
					
			}
			
		}
		return filteredUsers;
	}


	
	@Override
	@Transactional
	public Users getUserById(Long id) {
		Users user=userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
		return user;
	}
   
	  
        
}
