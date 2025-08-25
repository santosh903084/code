package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name="voters")
public class Voter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable=false)
	private Users user;
	
	@Column(nullable = false, unique = true)
	private String voterIdNumber;
	
	@Column(nullable = false)
	private String costituency;
	
	//added
	private boolean isVerified;
	private boolean isRegistered;
	public Voter()
	{
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
 
	public Users getUser() {
		return user;
	}
 
	public void setUser(Users user) {
		this.user = user;
	}
 
	public String getVoterIdNumber() {
		return voterIdNumber;
	}
 
	public void setVoterIdNumber(String voterIdNumber) {
		this.voterIdNumber = voterIdNumber;
	}
 
	public String getCostituency() {
		return costituency;
	}
 
	public void setCostituency(String costituency) {
		this.costituency = costituency;
	}
	
	//added
	public boolean getVerified()
	{
		return isVerified;
	}
	public void setVerified(boolean isVerified)
	{
		this.isVerified = isVerified;
	}
	
	//added
	public boolean getRegister()
	{
		return isRegistered;
	}
	public void setRegister(boolean isRegistered)
	{
		this.isRegistered = isRegistered;
	}
}
