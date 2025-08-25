package com.example.demo.serviceIntefaces;

import com.example.demo.dto.VoterRequest;
import com.example.demo.dto.VoterResponse;

public interface VoterService 
{
	VoterResponse registerVoter(VoterRequest request);
	VoterResponse getVoterDetails(String voterId);
}
