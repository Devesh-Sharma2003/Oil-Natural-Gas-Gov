package com.ongc.exceptions;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ongc.dto.responseDto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleEntityNotFoundException(EntityNotFoundException e){
		return new ResponseEntity<>(new ApiResponse<>(false,e.getMessage(),null,HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiResponse<?>> handleBadRequestException1(BadRequestException e){
		return new ResponseEntity<>(new ApiResponse<>(false,e.getMessage(),null,HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<?>> handleConstraintViolationException(ConstraintViolationException e){
		return new ResponseEntity<>(new ApiResponse<>(false,e.getMessage(),null,HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ApiResponse<?>> handleUnauthorizedException(UnauthorizedException e){
		return new ResponseEntity<>(new ApiResponse<>(false,e.getMessage(),null,HttpStatus.UNAUTHORIZED.value()),HttpStatus.UNAUTHORIZED);
	}
	
	
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<ApiResponse<?>> handleInternalServerErrorException(InternalServerErrorException e){
		return new ResponseEntity<>(new ApiResponse<>(false,e.getMessage(),null,HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleBaseException(Exception e){
		e.printStackTrace();
		return new ResponseEntity<>(new ApiResponse<>(false,"Unexpected error occured",null,HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
