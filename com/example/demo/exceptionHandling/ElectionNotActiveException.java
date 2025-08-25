package com.example.demo.exceptionHandling;

public class ElectionNotActiveException extends RuntimeException {
	public ElectionNotActiveException(String message)
	{
		super(message);
	}
}
