package com.example.demo.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(InvalidCandidateException.class)
	public ResponseEntity<ApiError> handleInvalidCandidate(InvalidCandidateException ex, HttpServletRequest req)
	{
		return build(HttpStatus.BAD_REQUEST, ex,req);
	}
	
	@ExceptionHandler(ElectionNotActiveException.class)
	public ResponseEntity<ApiError> handleElectionNotActive(ElectionNotActiveException ex, HttpServletRequest req)
	{
		return build(HttpStatus.BAD_REQUEST, ex,req);
	}
	
	@ExceptionHandler(AlreadyVotedException.class)
	public ResponseEntity<ApiError> handleAlreadyVoted(AlreadyVotedException ex, HttpServletRequest req)
	{
		return build(HttpStatus.CONFLICT, ex,req);
	}
	
	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<ApiError> handleUnauthorizedAccess(UnauthorizedAccessException ex, HttpServletRequest req)
	{
		return build(HttpStatus.FORBIDDEN, ex,req);
	}
	
	@ExceptionHandler(CandidateExceptionHandler.class)
    public ResponseEntity<String> handleCandidateNotFound(CandidateExceptionHandler ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	private ResponseEntity<ApiError> build(HttpStatus status, Exception ex,
			HttpServletRequest req) {
		ApiError err = new ApiError(status, ex.getMessage(),req.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
