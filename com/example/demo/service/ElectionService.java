package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResultResponse;
import com.example.demo.enums.ElectionStatus;
import com.example.demo.exceptionHandling.ElectionNotFoundException;
import com.example.demo.model.Candidate;
import com.example.demo.model.Election;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.ElectionRepository;
import com.example.demo.repository.VoteRepository;


@Service
public class ElectionService {
	
	@Autowired
	private ElectionRepository electionRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	//	create a new election and activate it
	
 
//	public Election createElection(Election election) {
//        election.setActive(true);
//        return electionRepository.save(election);
//    }
	public Election createElection(Election election) {
	    if (election.getStatus() == ElectionStatus.ONGOING) {
	        return electionRepository.save(election);
	    } else {
	        throw new IllegalArgumentException("Election must be ONGOING to be created.");
	    }
	}
	//get all active elections
 
 
	public List<Election> getActiveElections() {
	    return electionRepository.findByStatus(ElectionStatus.ONGOING);
	}
 
	
	//get elections by status
	public List<Election> getElectionsByStatus(ElectionStatus status) {
	        return electionRepository.findByStatus(status);
	    }
 
 
	
//get election details by counting votes per candidate
 
	 public List<ResultResponse> getElectionResults(Long electionId) {
	        Election election = electionRepository.findById(electionId)
	                .orElseThrow(() -> new ElectionNotFoundException("Election not found with ID: " + electionId));
 
	        List<Candidate> candidates = candidateRepository.findByElectionId(electionId);
 
	        List<ResultResponse> results = new ArrayList<>();
	        for (Candidate candidate : candidates) {
	            Long count = voteRepository.countByCandidateId(candidate.getId());
	            results.add(new ResultResponse(candidate.getName(), count));
	        }
 
	        return results;
	    }
}
		