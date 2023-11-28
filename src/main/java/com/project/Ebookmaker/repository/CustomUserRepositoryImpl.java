package com.project.Ebookmaker.repository;




import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


import com.project.Ebookmaker.entity.Users;



public class CustomUserRepositoryImpl implements CustomUserRepository{
	
	@Autowired
	EntityManager jpa;

	@Override
	@Transactional
	public Users findUser(String username){
		String sql = "select u from Users u where u.Username=:enteredName";
		final TypedQuery<Users> query = jpa.createQuery(sql,Users.class);
		query.setParameter("enteredName",username);
		List<Users> users = query.getResultList();
		if(users.size()!=0) {
			return users.get(0);
		}
		return null;
		
		

		
		
	}
    
	
	@Override
	@Transactional
	public Boolean findByUserName(String username) {
		
		String sql = "select u from Users u where u.Username=:enteredName";
		final TypedQuery<Users> query = jpa.createQuery(sql,Users.class);
		query.setParameter("enteredName",username);
		List<Users> users = query.getResultList();
		if(users.size()!=0) {
			return true;
		}
		return false;
	}

	  

}
