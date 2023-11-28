package com.project.Ebookmaker.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "USER")
public class Users implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long UserId;
	@Column(name="username")
	private String Username;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	
	



    
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@JsonIgnoreProperties("author")
	@OneToMany(mappedBy="author",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Books> books;
	
	public Users(){
		super();
		
	
		
		
		
		// TODO Auto-generated constructor stub
	}





	public Users(String username, String password,String email) {
		super();
		Username = username;
		this.password = password;
		this.email=email;
	}

	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public Long getUserId() {
		return UserId;
	}





	public void setUserId(Long userId) {
		UserId = userId;
	}





	public String getUsername() {
		return Username;
	}





	public void setUsername(String username) {
		Username = username;
	}









	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public Set<Role> getRoles() {
		return roles;
	}





	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}





	public List<Books> getBooks() {
		return books;
	}





	public void setBooks(List<Books> books) {
		this.books = books;
	}











}	
	
	
	
	

