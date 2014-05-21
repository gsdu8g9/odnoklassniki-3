package ru.odnoklassniki.tests.ui.api.locale;

import static ru.odnoklassniki.tests.ui.api.Messages.*;
import static ru.odnoklassniki.tests.ui.api.Messages.FAILED_READ_PROPERTIES;
import static ru.odnoklassniki.tests.ui.api.Messages.INVALID_LOCALE;
import static ru.odnoklassniki.tests.ui.api.Messages.LOCALE_NOT_FOUND;
import static ru.odnoklassniki.tests.ui.api.Messages.PROPERTY_UNDEFINED;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.runner.TestboxException;
import ru.odnoklassniki.tests.ui.api.WIBrowser;

public class LocaleManager {
	
	private static Properties propDefault = getProperties(System.getProperty("locale.default", "en"));
	private static Properties propCurrent;
	
	public static void autoDetect(WIBrowser browser) {
		// Auto-detect locale
		String locale = browser.getAttribute("//head/meta[@name='gwt:property']/@content");
		if (!locale.startsWith("locale=")) {
			throw new TestboxException(INVALID_LOCALE, locale);
		}
		setLocale(locale.substring(locale.indexOf("=") + 1));
	}
	
	private static Properties getProperties(String locale) {
		Properties properties = new Properties();
		InputStream is = LocaleManager.class.getResourceAsStream(locale + ".properties");
		if (is == null) {
			throw new TestboxException(LOCALE_NOT_FOUND, locale);			
		}
		try {
			try {
				properties.load(new InputStreamReader(is, "UTF-8"));
			} finally {
				is.close();
			}		
		} catch (IOException e) {
			throw new TestboxException(FAILED_READ_PROPERTIES, locale);			
		}
		
		// Check property file defines all enum values
		for (Text l : Text.values()) {
			if (properties.getProperty(l.name()) == null) {
				throw new TestboxException(PROPERTY_UNDEFINED, l.name(), locale);			
			}
		}
		
		return properties;
	}
	
	public static void setLocale(String locale) {
		propCurrent = getProperties(locale); 		
		Loggers.ui.debug(LOG_SET_LOCALE.getValue(locale));
	}
	
	public static Properties getDefault() {
		return propDefault; 
	}
	
	public static Properties getCurrent() {
		return propCurrent == null ? propDefault : propCurrent; 
	}

}
