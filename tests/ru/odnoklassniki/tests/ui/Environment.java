package ru.odnoklassniki.tests.ui;

/**
 * Test Environment settings 
 *
 */
public interface Environment {

	final String BASE_URL = "http://www.odnoklassniki.ru";
	final String USERNAME = "dummy_user123";
	final String PASSWORD = "pwd123456";

	/**
	 * Symbols acceptable for user name and surname  
	 */
	final String CORRECT_SYMBOLS = "()-";
	
	/**
	 * Symbols restricted for user name and surname  
	 */
	final String INVALID_SYMBOLS = "~`!@#$%^&*_=+\\|[{]}:\"'<>?/";

}
