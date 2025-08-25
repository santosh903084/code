package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Candidate;


 

@Repository
public interface CandidateRepository extends JpaRepository<Candidate , Long> {
 
	List<Candidate> findByElectionId(Long electionId);
 
}
