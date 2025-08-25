package com.example.demo.dto;

public class VoteRequestDTO {

    private Long voterId;
    private Long candidateId;
    private Long electionId;

    public VoteRequestDTO() 
    {
    	
    }

    public VoteRequestDTO(Long voterId, Long candidateId, Long electionId) {
        this.voterId = voterId;
        this.candidateId = candidateId;
        this.electionId = electionId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }
}
