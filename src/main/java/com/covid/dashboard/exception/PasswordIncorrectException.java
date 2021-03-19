package com.covid.dashboard.exception;

public class PasswordIncorrectException extends Exception {
	private static final long serialVersionUID = 9085786805969353508L;

	public PasswordIncorrectException(String errorMessage) {
		super(errorMessage);
	}
}