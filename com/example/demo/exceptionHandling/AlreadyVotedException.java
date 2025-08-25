package com.example.demo.exceptionHandling;

public class AlreadyVotedException extends RuntimeException
{
	public AlreadyVotedException(String message)
	{
		super(message);
	}
}
