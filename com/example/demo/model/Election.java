package com.example.demo.model;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.enums.ElectionStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

 
@Entity
public class Election {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
	//added
	@Column(nullable = false)
    private String title;
    private String description;
    
    //added
    @Column(nullable = false)
    private LocalDateTime startDate;
    
    //added
    @Column(nullable = false)
    private LocalDateTime endDate;
    
    //added
    @Enumerated(EnumType.STRING)
    private ElectionStatus status;
 
    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<Candidate> candidates;
 
	public Election(Long id, String title, String description, LocalDateTime startDate, LocalDateTime endDate, ElectionStatus status,
			List<Candidate> candidates) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.candidates = candidates;
	}
 
	public Election() {
	}
 
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 
	public String getTitle() {
		return title;
	}
 
	public void setTitle(String title) {
		this.title = title;
	}
 
	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}
 
	public LocalDateTime getStartDate() {
		return startDate;
	}
 
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
 
	public LocalDateTime getEndDate() {
		return endDate;
	}
 
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
 
	public ElectionStatus getStatus() {
		return status;
	}
 
	public void setStatus(ElectionStatus status) {
		this.status = status;
	}
 
	public List<Candidate> getCandidates() {
		return candidates;
	}
 
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
}
