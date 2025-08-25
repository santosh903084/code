package com.example.demo.exceptionHandling;

public class UnauthorizedAccessException extends RuntimeException
{
	public UnauthorizedAccessException(String message)
	{
		super(message);
	}
}
