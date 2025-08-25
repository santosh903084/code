package com.example.demo.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.enums.ElectionStatus;
import com.example.demo.model.Election;
import com.example.demo.service.ElectionService;



@RestController
public class ElectionController {
 
	@Autowired
	private ElectionService electionService;
	
 
	@PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Election> createElection(@RequestBody Election election) {
        return ResponseEntity.ok(electionService.createElection(election));
    }
	
 
 
		@GetMapping("/active")
		public ResponseEntity<List<Election>> getActiveElections() {
		    return ResponseEntity.ok(electionService.getActiveElections());
		}
		
 
	@GetMapping("/completed")
	    public ResponseEntity<List<Election>> getCompletedElections() {
	        return ResponseEntity.ok(electionService.getElectionsByStatus(ElectionStatus.COMPLETED));
	    }
	
 
		@GetMapping("/upcoming")
		public ResponseEntity<List<Election>> getUpcomingElections() {
		    return ResponseEntity.ok(electionService.getElectionsByStatus(ElectionStatus.UPCOMING));
		}
 
	@GetMapping("/{id}/results")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getElectionResults(@PathVariable Long id) {
        return ResponseEntity.ok(electionService.getElectionResults(id));
    }
 
 
}
