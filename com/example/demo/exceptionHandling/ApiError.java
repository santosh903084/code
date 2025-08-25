package com.example.demo.exceptionHandling;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ApiError 
{
	private Instant timestamp = Instant.now();
	private int status;
	private String error;
	private String message;
	private String path;
	
	public ApiError()
	{
		
	}
	
	public ApiError(HttpStatus status, String message, String path)
	{
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.message = message;
		this.path = path;
	}
	
	public void setTimestamp(Instant timestamp)
	{
		this.timestamp = timestamp;
	}
	public Instant getTimestamp()
	{
		return timestamp;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	public int getStatus()
	{
		return status;
	}
	
	public void setError(String error)
	{
		this.error = error;
	}
	public String getError()
	{
		return error;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	public String getMessage()
	{
		return message;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getPath()
	{
		return path;
	}
}
