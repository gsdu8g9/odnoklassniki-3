package ru.odnoklassniki.tests.common;

public class UnreachableCodeException extends RuntimeException {
	
	private static final long serialVersionUID = 7677701568589424687L;

	public UnreachableCodeException() {
		super("Unreachable code");
	}

}
