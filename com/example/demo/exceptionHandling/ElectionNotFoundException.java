package com.example.demo.exceptionHandling;

public class ElectionNotFoundException extends RuntimeException{
	 
public ElectionNotFoundException(String message) {
        super(message);
    }
	
}
