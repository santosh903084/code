package com.example.demo.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VoteRequestDTO;
import com.example.demo.service.VoteService;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // VOTER casts a vote
    @PostMapping
    @PreAuthorize("hasRole('VOTER')")
    public ResponseEntity<?> castVote(@RequestBody VoteRequestDTO voteRequest, Principal principal) {
        Long id = voteService.castVote(voteRequest, principal.getName());
        return ResponseEntity.status(201).body(java.util.Map.of("voteId", id));
    }

    // ADMIN gets total votes for an election
    @GetMapping("/{electionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> countVotes(@PathVariable Long electionId) 
    {
        long totalVotes = voteService.countVotes(electionId);
        return ResponseEntity.ok(java.util.Map.of("electionId",electionId, "count", totalVotes));
    }
    
    @PreAuthorize("hasRole('ADMIN')") // Only users with ADMIN role can access this
    @GetMapping("/result/{electionId}")
    public ResponseEntity<?> getElectionResults(@PathVariable Long electionId) {
        Map<String, Long> results = voteService.countVotesPerCandidate(electionId);
        return ResponseEntity.ok(java.util.Map.of("electionId", electionId,"results", results));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/count/candidate/{candidateId}")
    public ResponseEntity<Map<Long, Long>> countVotesByCandidate(@PathVariable Long candidateId) {
        Map<Long, Long> result = voteService.countVotesForCandidate(candidateId);
        return ResponseEntity.ok(result);
    }

}
