package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userdet")
public class Users {
	@Id
	private long id;
	private String username;
	private String password;
	private String Role;

	public Users() {}

	public Users(long id, String username, String password, String Role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.Role = Role;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return "ROLE_" + Role.toUpperCase();
	}


	public void setRole(String role) {
		Role = role;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", Role=" + Role + "]";
	}

}
