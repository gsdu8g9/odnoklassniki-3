package ru.odnoklassniki.tests.ui.api.selenium;

import java.io.File;
import java.net.URL;

import ru.odnoklassniki.tests.common.Utils;

import com.thoughtworks.selenium.DefaultSelenium;

/**
 * Class DefaultSelenium2 provide implementation of all additional functionality
 * declared in Selenium2 interface.
 * 
 * This class sends a command to Selenium Server only and returns result of
 * execution. Implementation of commands are located in JavaScript file
 * loaded by Selenium Server on the start, see file
 * src/ru/odnoklassniki/tests/wi/selenium/user-extensions.js
 * 
 */
public class DefaultSelenium2 extends DefaultSelenium implements Selenium2 {

	public DefaultSelenium2(String serverHost, int serverPort,
			String browserStartCommand, String browserURL) {
		super(serverHost, serverPort, browserStartCommand, browserURL);
		
		// Wrap commandProcessor to log all interaction with Selenium RC on client side
		commandProcessor = new LogggedCommandProcessor(commandProcessor);
	}

	public void open(URL url) {
		open(url.toString());
	}

	@Override
	public void setShouldHighlightElement(boolean value) {
		commandProcessor.doCommand("setShouldHighlightElement",
				new String[] { Boolean.toString(value) });
	}

	@Override
	public void getHtmlSource(File file) {
		Utils.setText(file, getHtmlSource());
	}

}
