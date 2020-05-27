package br.com.caderneta.service.common.exceptions.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.caderneta.service.common.exceptions.AuthenticationCredentialsNotFoundException;
import br.com.caderneta.service.common.exceptions.AuthorizationException;
import br.com.caderneta.service.common.exceptions.DataIntegrityViolationException;
import br.com.caderneta.service.common.exceptions.EmailException;
import br.com.caderneta.service.common.exceptions.EmptyResultDataAccessException;
import br.com.caderneta.service.common.exceptions.FileException;
import br.com.caderneta.service.common.exceptions.InternalException;
import br.com.caderneta.service.common.exceptions.NotFoundException;
import br.com.caderneta.service.common.exceptions.QuantidadeParcelasException;
import br.com.caderneta.service.common.exceptions.UserException;
import br.com.caderneta.service.common.exceptions.exception.StandardError;
import br.com.caderneta.service.common.exceptions.exception.ValidationError;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(UserException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public StandardError userFound(UserException e, HttpServletRequest request) {
		
		return StandardError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.mensagem(e.getMessage())
				.metodo(request.getMethod())
				.path(request.getRequestURI())
				.timestamp(new Date())
				.build();
	} 
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public StandardError emptyResultNotFound(EmptyResultDataAccessException e, HttpServletRequest request) {
		
		return StandardError.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.mensagem(e.getMessage())
				.timestamp(new Date())
				.build();
	} 
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalException.class)
	public StandardError internalException(InternalException e) {
		
		return StandardError.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.mensagem(e.getMessage())
				.timestamp(new Date())
				.build();
	} 
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public StandardError dataIntegritytNotFound(DataIntegrityViolationException e, HttpServletRequest request) {
		
		return StandardError.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.mensagem(e.getMessage())
				.metodo(request.getMethod())
				.path(request.getRequestURI())
				.timestamp(new Date())
				.build();
	} 
	
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public StandardError validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Error de validação", new Date());
		e.getBindingResult().getFieldErrors().forEach(f -> error.addError(f.getField(), f.getDefaultMessage()));
		return error;
	} 
	
	@ResponseBody
	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public StandardError authorizationException(AuthorizationException e, HttpServletRequest request) {
		
		return StandardError.builder()
				.status(HttpStatus.FORBIDDEN.value())
				.mensagem(e.getMessage())
				.metodo(request.getMethod())
				.path(request.getRequestURI())
				.timestamp(new Date())
				.build();
	} 
	
	@ResponseBody
	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public StandardError authenticationException(AuthenticationCredentialsNotFoundException e, HttpServletRequest request) {
		
		return StandardError.builder()
				.status(HttpStatus.FORBIDDEN.value())
				.mensagem(e.getMessage())
				.metodo(request.getMethod())
				.path(request.getRequestURI())
				.timestamp(new Date())
				.build();
	}
	
	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public StandardError notFound(NotFoundException e, HttpServletRequest request) {
		
		return StandardError.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.mensagem(e.getMessage())
				.metodo(request.getMethod())
				.path(request.getRequestURI())
				.timestamp(new Date())
				.build();
	}
	
	@ResponseBody
	@ExceptionHandler(EmailException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public StandardError emailException(EmailException e, HttpServletRequest request) {
		
		return StandardError.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.mensagem(e.getMessage())
				.metodo(request.getMethod())
				.path(request.getRequestURI())
				.timestamp(new Date())
				.build();
	}
	
	@ResponseBody
	@ExceptionHandler(FileException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public StandardError fileException(FileException e, HttpServletRequest request) {
		
		return StandardError.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.mensagem(e.getMessage())
				.metodo(request.getMethod())
				.path(request.getRequestURI())
				.timestamp(new Date())
				.build();
	}
	
	@ResponseBody
	@ExceptionHandler(QuantidadeParcelasException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public StandardError parcelasException(QuantidadeParcelasException e, HttpServletRequest request) {
		
		return StandardError.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.mensagem(e.getMessage())
				.build();
	}
}
