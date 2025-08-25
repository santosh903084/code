package com.example.demo.exceptionHandling;

public class InvalidCandidateException extends RuntimeException 
{
	public InvalidCandidateException (String message)
	{
		super(message);
	}
}
