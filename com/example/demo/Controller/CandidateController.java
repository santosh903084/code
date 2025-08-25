package com.example.demo.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CandidateDTO;
import com.example.demo.model.Election;
import com.example.demo.service.CandidateService;


@RestController
@RequestMapping("/candidate")
public class CandidateController 
{

 
	@Autowired
    private CandidateService service;
 
    @PostMapping
    public CandidateDTO addCandidate(@RequestBody CandidateDTO candidateDTO)
    {
        return service.addCandidate(candidateDTO);
    }
 
    @GetMapping
    public List<CandidateDTO> getAllCandidates()
    {
        return service.getAllCandidates();
    }
 
    @GetMapping("/{id}")
    public CandidateDTO getById(@PathVariable Long id)
    {
        return service.getById(id);
    }
 
    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id)
    {
        service.deleteCandidate(id);
    }
 
    @GetMapping("/election/{id}")
    public Optional<Election> getCandidatesByElectionId(@PathVariable Long id) 
    {
        return service.getCandidatesByElectionId(id);
    }
    @PutMapping("/{id}")
    public CandidateDTO updateCandidate(@PathVariable Long id, @RequestBody CandidateDTO candidateDTO) 
    {
        return service.updateCandidate(id, candidateDTO);
    }
 
}