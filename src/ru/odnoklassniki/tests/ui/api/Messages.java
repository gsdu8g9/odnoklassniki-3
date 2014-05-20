package ru.odnoklassniki.tests.ui.api;

import ru.odnoklassniki.tests.common.IMessage;

public enum Messages implements IMessage {

	INVALID_LOCALE("Invalid browser locale value '%s'"),
	
	FAILED_READ_PROPERTIES("Failed read properties %s"),
	PROPERTY_UNDEFINED("Property not found %s (%s)", "Fix property file and try again"),
	LOCALE_NOT_FOUND("Locale not found '%s'", "Add correspondinf property file"),
	LOCALE_UNDEFINED("Locale is undefined", "Define locale first"),
	
	
	ERR_SELENIUM_INVALID_URL2(
			"Invalid Selenium URL %s"),
	ERR_INVALID_URL2(
			"Invalid URL %s"),

	ERR_FAILED_START_SELENIUM_SERVER(
			"Faield start Selenium Server"),
	ERR_FAILED_CLOSE_BROWSER(
			"Faield close browser"),
	ERR_ARGUMENT_IS_NULL(
			"Argument %s must not be null", "Fix code"),
	ERR_ARGUMENT_IS_EMPTY(
			"Argument %s must not be empty", "Fix code"),

	TEST_EXPECTED_VISIBLE("Expected visible %s"), 
	TEST_EXPECTED_INVISIBLE("Expected invisible %s"), 
	TEST_EXPECTED_ACCESSIBLE("Expected accessible %s"), 
	TEST_UNEXPECTED_ACCESSIBLE("Unexpected accessible %s"), 
	TEST_EXPECTED_DISAPPEAR("Expected %s disappear after %d seconds"), 
	
	TEST_DIALOG_DOESNT_DISAPPEAR("Dialog \"%s\" doesn't disappear after %d seconds"),

	TEST_EXPECTED_DISABLED("Expected disabled %s"),
	TEST_EXPECTED_ENABLED("Expected enabled %s"),
	
	TEST_EXPECTED_VALUE("Expected %s has value \"%s\""),
	TEST_UNEXPECTED_VALUE("Unexpected %s has value \"%s\""),

	LOG_ELEMENT_VISIBLE("%s is visible"),
	LOG_ELEMENT_INVISIBLE("%s is invisible"),
	LOG_ELEMENT_ACCESSIBLE("%s is accessible"),
	LOG_ELEMENT_IS_INACCESSIBLE("%s is inaccessible"),
	LOG_ELEMENT_WAIT_VISIBLE("Waiting for %s to become visible..."),
	LOG_ELEMENT_WAIT_INVISIBLE("Waiting for %s to become invisible..."),
	LOG_ELEMENT_WAIT_ACCESSIBLE("Waiting for %s to become accessible..."),
	LOG_ELEMENT_WAIT_INACCESSIBLE("Waiting for %s to become inaccessible..."),
	LOG_ELEMENT_WAIT_INVISIBLE_FOR("Waiting for %s is invisible for %d seconds..."),
	
	LOG_SELECTOR_SETVALUE("Select \"%2$s\" at %1$s"),
	
	LOG_CHECKBOX_CHECK("Check %s"),
	LOG_CHECKBOX_UNCHECK("Uncheck %s"),
	
	VIP_HOSTNAME_UNRESOLVED("Unresolved VIP %s hostname", "Fix test or test environment"),

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
