package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CandidateDTO;
import com.example.demo.exceptionHandling.CandidateExceptionHandler;
import com.example.demo.model.Candidate;
import com.example.demo.model.Election;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.ElectionRepository;


@Service
public class CandidateService {
	@Autowired
	private CandidateRepository repo;
 
	@Autowired
	private ElectionRepository repoElection;
 
	public CandidateDTO addCandidate(CandidateDTO candidateDTO) 
	{
	    Candidate candidate = new Candidate();
	    candidate.setName(candidateDTO.getName());
	    candidate.setParty(candidateDTO.getParty());
	    Election election = repoElection.findById(candidateDTO.getElectionId()).orElseThrow(()->new RuntimeException("Election id is not found : " + candidateDTO.getElectionId()));
	    candidate.setElection(election);
 
	    Candidate saved = repo.save(candidate);
	    return convertToDTO(saved);
	}
 
	public List<CandidateDTO> getAllCandidates() 
	{
	    return repo.findAll().stream()
	            .map(this::convertToDTO)
	            .collect(Collectors.toList());
	}
 
	public CandidateDTO getById(Long id)
	{
	    Candidate candidate = repo.findById(id)
	            .orElseThrow(() -> new CandidateExceptionHandler(id));
	    return convertToDTO(candidate);
	}
 
	public void deleteCandidate(Long id)
	{
	    repo.deleteById(id);
	}
 
	public Optional<Election> getCandidatesByElectionId(Long electionId) 
	{
	    return repoElection.findById(electionId);
	}
 
	private CandidateDTO convertToDTO(Candidate candidate) 
	{
	    return new CandidateDTO(
	            candidate.getId(),
	            candidate.getName(),
	            candidate.getParty(),
	            candidate.getElection() != null ? candidate.getElection().getId() : null
	    );
	}
	public CandidateDTO updateCandidate(Long id, CandidateDTO candidateDTO) 
	{
	    Candidate existingCandidate = repo.findById(id).orElseThrow(()->new CandidateExceptionHandler(id));
 
	    existingCandidate.setName(candidateDTO.getName());
	    existingCandidate.setParty(candidateDTO.getParty());
	    if(candidateDTO.getElectionId() != null) 
	    {
	    	Election election = repoElection.findById(candidateDTO.getElectionId()).orElseThrow(()-> new RuntimeException("Election id is not found :" +candidateDTO.getElectionId()));
	    	existingCandidate.setElection(election);
	    }

 
	    Candidate updated = repo.save(existingCandidate);
	    return convertToDTO(updated);
	}
 
 
}
