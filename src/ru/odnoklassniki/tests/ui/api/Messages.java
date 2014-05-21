package ru.odnoklassniki.tests.ui.api;

import ru.odnoklassniki.tests.common.IMessage;
import ru.odnoklassniki.tests.common.Utils;

public enum Messages implements IMessage {

	INVALID_LOCALE("Invalid browser locale value '%s'"),
	
	FAILED_READ_PROPERTIES("Failed read properties %s"),
	PROPERTY_UNDEFINED("Property not found %s (%s)"),
	LOCALE_NOT_FOUND("Locale not found '%s'"),
	LOCALE_UNDEFINED("Locale is undefined"),
	
	ERR_FAILED_START_SELENIUM_SERVER("Faield start Selenium Server, %s"),
	ERR_FAILED_CLOSE_BROWSER("Faield close browser"),
	ERR_ARGUMENT_IS_NULL("Argument %s must not be null"),
	ERR_ARGUMENT_IS_EMPTY("Argument %s must not be empty"),
	ERR_ARGUMENT_NO_SIZE("Argument %s has no size"),

	TEST_VALUE("%s value"),
	TEST_EXPECTED_VISIBLE("Expected visible %s"), 
	TEST_EXPECTED_INVISIBLE("Expected invisible %s"), 
	
	TEST_EXPECTED_VALUE("Expected %s has value \"%s\""),
	TEST_UNEXPECTED_VALUE("Unexpected %s has value \"%s\""),

	TEST_CHECKED("Expected %s checked"),
	TEST_UNCHECKED("Expected %s unchecked"),

	LOG_CLICK("Click %s"),
	LOG_TYPE("Type \"%s\" at %s"),	
	LOG_ELEMENT_VISIBLE("%s is visible"),
	LOG_ELEMENT_INVISIBLE("%s is invisible"),
	LOG_ELEMENT_WAIT_VISIBLE("Waiting for %s to become visible..."),
	LOG_ELEMENT_WAIT_INVISIBLE("Waiting for %s to become invisible..."),
	
	LOG_SELECTOR_SETVALUE("Select \"%2$s\" at %1$s"),
	
	LOG_CHECKBOX_CHECK("Check %s"),
	LOG_CHECKBOX_UNCHECK("Uncheck %s"),
	
	LOG_SET_LOCALE("Set locale %s"),
	LOG_OPEN_BROWSER("Open browser %s"),	
	LOG_CLOSE_BROWSER("Close browser")
	;
	
	private String message;

	Messages(String message) {
		this.message = message;
	}

	@Override
	public String getValue(Object... params) {
		return Utils.format(message, params);
	}

}
