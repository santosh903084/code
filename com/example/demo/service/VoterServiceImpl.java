package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.VoterRequest;
import com.example.demo.dto.VoterResponse;
import com.example.demo.model.Users;
import com.example.demo.model.Voter;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.VoterRepository;
import com.example.demo.serviceIntefaces.VoterService;


@Service
public class VoterServiceImpl implements VoterService {
	
	private final VoterRepository voterRepo;
	private final UserRepo userRepo;
	
	public VoterServiceImpl(VoterRepository voterRepo,UserRepo userRepo) {
		this.userRepo = userRepo;
		this.voterRepo = voterRepo;
	}
	
 
	@Override
	public VoterResponse registerVoter(VoterRequest request) {
		if(voterRepo.existsByVoterIdNumber(request.voterIdNumber())) {
			throw new RuntimeException("Voter already registered with this ID number");
		}
		
		Users user = userRepo.findById(request.userId())
			.orElseThrow(() -> new RuntimeException("User not Found"));
		
		Voter voter = new Voter();
		voter.setUser(user);
		voter.setVoterIdNumber(request.voterIdNumber());
		voter.setCostituency(request.constituency());
		
		Voter saved = voterRepo.save(voter);
				
		return new VoterResponse(saved.getId(), user.getUsername(), saved.getVoterIdNumber(), saved.getCostituency());
	}
 
	@Override
	public VoterResponse getVoterDetails(String voterId) {
		Voter voter = voterRepo.findByVoterIdNumber(voterId)
				.orElseThrow(()-> new RuntimeException("Voter not Found"));
		return new VoterResponse(voter.getId(), voter.getUser().getUsername(), voter.getVoterIdNumber(), voter.getCostituency());
	}
 
}