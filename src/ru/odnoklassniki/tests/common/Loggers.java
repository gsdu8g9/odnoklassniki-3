package ru.odnoklassniki.tests.common;

import org.slf4j.Logger;

/**
 * Collection of loggers
 * 
 */
public class Loggers {

	/**
	 * Framework logger. Used for any framework activities
	 */
	public final static Logger framework = LogFactory.getLogger("framework");

	/**
	 * Web interface logger. Used for human readable actions
	 */
	public final static Logger ui = LogFactory.getLogger("ui");

	/**
	 * Selenium logger. Used for protocol interaction with Selenium RC
	 */
	public final static Logger selenium = LogFactory.getLogger("selenium");

}
