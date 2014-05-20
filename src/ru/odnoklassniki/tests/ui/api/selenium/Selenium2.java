package ru.odnoklassniki.tests.ui.api.selenium;

import java.io.File;
import java.net.URL;

import com.thoughtworks.selenium.Selenium;

/**
 * Interface Selenium2 extends Selenium functionality with additional commands.
 * 
 */
public interface Selenium2 extends Selenium {

	void open(URL url);
	
	/**
	 * Switch on/off highlighting of all HTML elements during execution of all
	 * Selenium commands. Highlighting helps visually control browser's command
	 * execution.
	 * 
	 * @param value
	 *            set true to enable highlighting
	 */
	void setShouldHighlightElement(boolean value);

	void getHtmlSource(File file);
	
}
