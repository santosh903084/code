package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "vote", uniqueConstraints = {
		@UniqueConstraint(name = "uk_vote_voter_election", columnNames = {"voter_id", "election_id"})
})
public class Vote 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vote_id")
	private Long vote_id;
	
	@ManyToOne
	@JoinColumn(name = "voter_id", nullable = false)
	private Voter voter;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "election_id", nullable = false)
	private Election election;
	
	@Column(name = "timestamp", nullable = false, updatable = false)
	private LocalDateTime timestamp;
	
	@PrePersist
	protected void onCreate()
	{
		this.timestamp = LocalDateTime.now();
	}
	public Vote()
	{
		
	}
	
	public Vote(Long vote_id, Voter voter, Candidate candidate, Election election, LocalDateTime timestamp)
	{
		this.vote_id = vote_id;
		this.voter = voter;
		this.candidate = candidate;
		this.election = election;
		this.timestamp = timestamp;
	}
	
	public void setVoteId(Long vote_id)
	{
		this.vote_id = vote_id;
	}
	public Long getVoteId()
	{
		return vote_id;
	}
	
	public void setVoter(Voter voter)
	{
		this.voter = voter;
	}
	public Voter getVoter()
	{
		return voter;
	}
	
	public void setCandidate(Candidate candidate)
	{
		this.candidate = candidate;
	}
	public Candidate getCandidate()
	{
		return candidate;
	}
	
	public void setElection(Election election)
	{
		this.election = election;
	}
	public Election getElection()
	{
		return election;
	}
	
	public void setTimestamp(LocalDateTime timestamp)
	{
		this.timestamp = timestamp;
	}
	public LocalDateTime getTimestamp()
	{
		return timestamp;
	}
}
