package ru.odnoklassniki.tests.ui.api;

import ru.odnoklassniki.tests.common.IMessage;

public enum Messages implements IMessage {

	INVALID_LOCALE("Invalid browser locale value '%s'"),
	
	FAILED_READ_PROPERTIES("Failed read properties %s"),
	PROPERTY_UNDEFINED("Property not found %s (%s)", "Fix property file and try again"),
	LOCALE_NOT_FOUND("Locale not found '%s'", "Add correspondinf property file"),
	LOCALE_UNDEFINED("Locale is undefined", "Define locale first"),
	
	ERR_FAILED_START_SELENIUM_SERVER("Faield start Selenium Server, %s"),
	ERR_FAILED_CLOSE_BROWSER("Faield close browser"),
	ERR_ARGUMENT_IS_NULL("Argument %s must not be null", "Fix code"),
	ERR_ARGUMENT_IS_EMPTY("Argument %s must not be empty", "Fix code"),
	ERR_ARGUMENT_NO_SIZE("Argument %s has no size"),

	TEST_VALUE("Input %s value"),
	TEST_EXPECTED_VISIBLE("Expected visible %s"), 
	TEST_EXPECTED_INVISIBLE("Expected invisible %s"), 
	
	TEST_EXPECTED_VALUE("Expected %s has value \"%s\""),
	TEST_UNEXPECTED_VALUE("Unexpected %s has value \"%s\""),

	TEST_CHECKED("Expected %s checked"),
	TEST_UNCHECKED("Expected %s unchecked"),

	LOG_ELEMENT_VISIBLE("%s is visible"),
	LOG_ELEMENT_INVISIBLE("%s is invisible"),
	LOG_ELEMENT_WAIT_VISIBLE("Waiting for %s to become visible..."),
	LOG_ELEMENT_WAIT_INVISIBLE("Waiting for %s to become invisible..."),
	
	LOG_SELECTOR_SETVALUE("Select \"%2$s\" at %1$s"),
	
	LOG_CHECKBOX_CHECK("Check %s"),
	LOG_CHECKBOX_UNCHECK("Uncheck %s"),
	;
	
	private String m_problem;
	private String m_solution;

	Messages(String problem) {
		m_problem = problem;
	}

	Messages(String problem, String solution) {
		m_problem = problem;
		m_solution = solution; 
	}

	@Override
	public String getProblem(Object... params) {
		return ru.odnoklassniki.tests.common.Messages.format(m_problem, params);
	}

	@Override
	public String getSolution(Object... params) {
		return ru.odnoklassniki.tests.common.Messages.format(m_solution, params);
	}
	
}
