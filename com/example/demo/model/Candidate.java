package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
 
@Entity
public class Candidate {
	
	@Id
	private Long id;
	private String name;
	private String party;
	@ManyToOne
	@JoinColumn(name = "election_id" , nullable = false)
	private Election election;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;
	
	public Candidate()
	{
	}
 
	public Candidate(Long id, String name, String party, Election election, Users user) 
	{
		super();
		this.id = id;
		this.name = name;
		this.party = party;
		this.election = election;
		this.user = user;
	}
 
	public Long getId() 
	{
		return id;
	}
 
	public void setId(Long id) 
	{
		this.id = id;
	}
 
	public String getName()
	{
		return name;
	}
 
	public void setName(String name)
	{
		this.name = name;
	}
 
	public String getParty() 
	{
		return party;
	}
 
	public void setParty(String party)
	{
		this.party = party;
	}
 
	public Election getElection() 
	{
		return election;
	}
 
	public void setElection(Election election) 
	{
		this.election = election;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	

}