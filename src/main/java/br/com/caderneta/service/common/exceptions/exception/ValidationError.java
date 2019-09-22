package br.com.caderneta.service.common.exceptions.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	@Getter
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String mensagem, Date timestamp) {
		super(status, mensagem, null, null, timestamp);
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
}
