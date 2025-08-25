package com.example.demo.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Vote;



@Repository
public interface VoteRepository extends JpaRepository<Vote,Long>
{
	@Query("select v from Vote v where v.election.id = :electionId")
	List<Vote> findByElectionId(@Param("electionId") Long electionId);
	
	@Query("select v from Vote v where v.voter.id = :voterId")
	List<Vote> findByVoterId(@Param("voterId")Long electionId);
	
	@Query("select(count(v) > 0) from Vote v where v.voter.id = :voterId and v.election.id = :electionId")
	boolean existsByVoterAndElection(@Param("voterId") Long voterId, @Param("electionId") Long electionId);

	@Query("SELECT v.candidate.name, COUNT(v) FROM Vote v WHERE v.election.id = :electionId GROUP BY v.candidate.name")
	Map<String, Long> countVotesGroupedByCandidate(@Param("electionId") Long electionId);

	long countByElectionId(Long electionId);
	
	long countByCandidateId(Long candidateId);
	
}
