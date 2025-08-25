package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.VoterRequest;
import com.example.demo.dto.VoterResponse;
import com.example.demo.serviceIntefaces.VoterService;

@RestController
@RequestMapping("/voters")
public class VoterController {
	
	//@Autowired
	private final VoterService voterService;
	
	public VoterController(VoterService voterService) {
		this.voterService = voterService;
	}
	
	@PostMapping("/register")
	public VoterResponse registerVoter(@RequestBody VoterRequest request)
	{
		return voterService.registerVoter(request);
	}
	
	@GetMapping("/{id}")
	public VoterResponse getVoterDetails(@PathVariable String id) {
		return voterService.getVoterDetails(id);
	}
 
}