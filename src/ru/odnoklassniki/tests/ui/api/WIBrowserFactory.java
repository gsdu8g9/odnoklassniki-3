package ru.odnoklassniki.tests.ui.api;

import static ru.odnoklassniki.tests.ui.api.Messages.ERR_FAILED_START_SELENIUM_SERVER;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.common.Utils;
import ru.odnoklassniki.tests.runner.Testbox;
import ru.odnoklassniki.tests.runner.TestboxException;
import ru.odnoklassniki.tests.ui.api.locale.LocaleManager;

public class WIBrowserFactory {

	private static final String SELENIUM_URL = System.getProperty("selenium", "http://localhost:4444");
	private static final String SELENIUM_LOG = System.getProperty("selenium.log");
	private static final String SELENIUM_BROWSER = System.getProperty("selenium.browser", "*firefox");
	private static final String SELENIUM_TIMEOUT = System.getProperty("selenium.timeout", "30000");

	private static boolean useEmbededSeleniumServer = System.getProperty("selenium.embedded", "true").equals("true");
	private static boolean isSeleniumRunning = false;
	private static URL seleniumUrl = Utils.getURL(SELENIUM_URL);
	private static SeleniumServer server;

	private static void startEmbededSeleniumServer() {

		File tmp = Testbox.getTmpFolder();

		File profile = new File(tmp, "profile");
		Utils.clean(profile);

		File userExtensions = new File(profile, "user-extensions.js");
		Utils.copy(WIBrowserFactory.class.getResourceAsStream("selenium/user-extensions.js"), userExtensions);

		File prefs = new File(profile, "prefs.js");
		Utils.copy(WIBrowserFactory.class.getResourceAsStream("selenium/profile/prefs.js"), prefs);

		try {
			File log = SELENIUM_LOG == null ? new File(tmp, "selenium.log") : new File(SELENIUM_LOG);

			RemoteControlConfiguration config = new RemoteControlConfiguration();
			config.setLogOutFile(log);
			config.setUserExtensions(userExtensions);
			config.setFirefoxProfileTemplate(new File(System.getProperty("selenium.profile", profile.getAbsolutePath())));
			config.setPort(seleniumUrl.getPort());

			Loggers.selenium.info("Port : " + config.getPort());
			Loggers.selenium.info("Profile : " + config.getFirefoxProfileTemplate());
			Loggers.selenium.info("Log : " + config.getLogOutFile());

			server = new SeleniumServer(config);
			server.boot();
			server.start();
		} catch (Exception e) {
			server = null;
			throw new TestboxException(ERR_FAILED_START_SELENIUM_SERVER, e);
		}
	}

	public static WIBrowser getNewBrowser(String aUrl) {
		URL url = Utils.getURL(aUrl);

		// Start Selenium server only when first test called getNewBrowser()
		if (useEmbededSeleniumServer) {
			if (!isSeleniumRunning) {
				startEmbededSeleniumServer();
				isSeleniumRunning = true;
			}
		}

		URL baseUrl = Utils.getURL(url.getProtocol(), url.getHost(), url.getPort(), "");

		WIBrowser browser = new WIBrowser(seleniumUrl.getHost(), seleniumUrl.getPort(), SELENIUM_BROWSER,
		        baseUrl.toString());

		browser.setTimeout(SELENIUM_TIMEOUT);

		// Highlight used HTML elements to get visual control of executed
		// operations
		browser.setShouldHighlightElement(true);

		browser.windowMaximize();

		browser.open(aUrl);

		LocaleManager.autoDetect(browser);

		return browser;
	}

	private WIBrowserFactory() {
	}

	public static void main(String[] args) {
		startEmbededSeleniumServer();
	}

}
