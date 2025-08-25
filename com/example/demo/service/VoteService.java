package com.example.demo.service;




import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.VoteRequestDTO;
import com.example.demo.enums.ElectionStatus;
import com.example.demo.exceptionHandling.AlreadyVotedException;
import com.example.demo.exceptionHandling.ElectionNotActiveException;
import com.example.demo.exceptionHandling.InvalidCandidateException;
import com.example.demo.exceptionHandling.UnauthorizedAccessException;
import com.example.demo.model.Candidate;
import com.example.demo.model.Election;
import com.example.demo.model.Users;
import com.example.demo.model.Vote;
import com.example.demo.model.Voter;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.repository.ElectionRepository;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.VoteRepository;
import com.example.demo.repository.VoterRepository;


@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private UserRepo userRepository;
    
    public VoteService(VoteRepository voteRepository, VoterRepository voterRepository,
    		CandidateRepository candidateRepository,ElectionRepository electionRepository,
    		UserRepo userRepository)
    {
    	this.voteRepository = voteRepository;
    	this.voterRepository = voterRepository;
    	this.candidateRepository = candidateRepository;
    	this.electionRepository = electionRepository;
    	this.userRepository = userRepository;
    }
    // Cast a vote (ensures one vote per voter per election)
    public Long castVote(VoteRequestDTO dto, String username) {
        Users user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UnauthorizedAccessException("User not found"));
        
        Voter voter = voterRepository.findById(user.getId())
        		.orElseThrow(() -> new UnauthorizedAccessException("User is not registered as a voter"));
        if(!voter.getRegister())
        {
        	throw new UnauthorizedAccessException("Voter is not registered to vote");
        }
        Election election = electionRepository.findById(dto.getElectionId())
            .orElseThrow(() -> new RuntimeException("Election not found"));

        LocalDateTime now = LocalDateTime.now();
        boolean isWindow = !now.isBefore(election.getStartDate()) && !now.isAfter(election.getEndDate());
        if(election.getStatus() == ElectionStatus.COMPLETED || !isWindow || election.getStatus() != ElectionStatus.ONGOING)
        {
        	throw new ElectionNotActiveException("Election is not active");
        }
     
        if(voteRepository.existsByVoterAndElection(voter.getId(), election.getId()))
        {
        	throw new AlreadyVotedException("Voter has already voted in this election");
        }
        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
            .orElseThrow(() -> new RuntimeException("Candidate not found"));
        if(!candidate.getElection().getId().equals(election.getId()))
        {
        	throw new InvalidCandidateException("Candidate does not belong to the specified election");
        }
        if(candidate.getUser().getId() == user.getId())
        {
        	throw new InvalidCandidateException("Yoou cannot vote for yourself");
        }
//        if(candidate.getUser().getId().equals(user.getId()))
//        {
//        	throw new InvalidCandidateException("Yoou cannot vote for yourself");
//        }

        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setElection(election);
        vote.setCandidate(candidate);
        vote.setTimestamp(now);

        return voteRepository.save(vote).getVoteId();
    }

    // Count total votes for an election
    public long countVotes(Long electionId) {
        return voteRepository.countByElectionId(electionId);
    }

    //counts votes per candidate
	public Map<String, Long> countVotesPerCandidate(Long electionId) 
	{
		Election election = electionRepository.findById(electionId)
		        .orElseThrow(() -> new RuntimeException("Election not found"));
		return voteRepository.countVotesGroupedByCandidate(electionId);

	}
	
	//counts votes per candidate using candidate id
	public Map<Long, Long> countVotesForCandidate(Long candidateId) {
	    Candidate candidate = candidateRepository.findById(candidateId)
	        .orElseThrow(() -> new RuntimeException("Candidate not found"));

	    Long voteCount = voteRepository.countByCandidateId(candidateId);
	    Map<Long, Long> result = new HashMap<>();
	    result.put(candidateId, voteCount);
	    return result;
	}

}
