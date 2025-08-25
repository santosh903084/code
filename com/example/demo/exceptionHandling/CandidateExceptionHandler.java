package com.example.demo.exceptionHandling;

public class CandidateExceptionHandler extends RuntimeException
{
    public CandidateExceptionHandler(Long id)
    {
    	super("Candidate Id is not found : " +id);
    }
}
