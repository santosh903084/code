package com.example.demo.dto;


public class ResultResponse {
	 private String candidateName;
	    private Long votesCount;

	    public ResultResponse(String candidateName, Long votesCount) {
	        this.candidateName = candidateName;
	        this.votesCount = votesCount;
	    }

	    public String getCandidateName() {
	        return candidateName;
	    }

	    public void setCandidateName(String candidateName) {
	        this.candidateName = candidateName;
	    }

	    public Long getVotesCount() {
	        return votesCount;
	    }

	    public void setVotesCount(Long votesCount) {
	        this.votesCount = votesCount;
	    }
}
