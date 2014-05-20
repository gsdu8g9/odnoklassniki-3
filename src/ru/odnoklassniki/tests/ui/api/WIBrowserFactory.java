package ru.odnoklassniki.tests.ui.api;

import static ru.odnoklassniki.tests.ui.api.Messages.ERR_SELENIUM_INVALID_URL2;
import static ru.odnoklassniki.tests.ui.api.Messages.ERR_SELENIUM_INVALID_URL2;
import static ru.odnoklassniki.tests.ui.api.Messages.ERR_FAILED_CLOSE_BROWSER;
import static ru.odnoklassniki.tests.ui.api.Messages.ERR_FAILED_START_SELENIUM_SERVER;
import static ru.odnoklassniki.tests.ui.api.Messages.ERR_INVALID_URL2;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;
import org.slf4j.Logger;

import ru.odnoklassniki.tests.common.LogFactory;
import ru.odnoklassniki.tests.common.Utils;
import ru.odnoklassniki.tests.runner.Testbox;
import ru.odnoklassniki.tests.ui.api.locale.Text;


public class WIBrowserFactory {

	private final static Logger log = LogFactory.getLogger(WIBrowserFactory.class);

	private static final String SELENIUM_URL = System.getProperty("selenium", "http://localhost:4444");
	private static final String SELENIUM_PORT = System.getProperty("selenium.port", "" + RemoteControlConfiguration.getDefaultPort());
	private static final String SELENIUM_LOG = System.getProperty("selenium.log");
	private static final String SELENIUM_BROWSER = System.getProperty("selenium.browser", "*firefox");

	private static boolean useEmbededSeleniumServer = false;
	private static boolean isSeleniumRunning = false;
	private static URL seleniumUrl;
	private static SeleniumServer server;
	private static final List<WIBrowser> createdBrowsers = new ArrayList<WIBrowser>();

	public static URL getSeleniumURL() {
		return seleniumUrl == null ? seleniumUrl = Utils.getURL(SELENIUM_URL, ERR_SELENIUM_INVALID_URL2) : seleniumUrl;
	}

	private static void startEmbededSeleniumServer() {

		File tmp = Testbox.getTmpFolder();

		File profile = new File(tmp, "profile");
		Utils.clean(profile);

		File download = new File(tmp, "download");
		Utils.clean(download);

		File userExtensions = new File(profile, "user-extensions.js");
		Utils.copy(WIBrowserFactory.class
				.getResourceAsStream("selenium/user-extensions.js"),
				userExtensions);

		File prefs = new File(profile, "prefs.js");
		Utils.copy(WIBrowserFactory.class
				.getResourceAsStream("selenium/profile/prefs.js"), prefs);

		String path = download.getAbsolutePath();
		// Sometimes Firefox doesn't understand Windows path with single
		// slash "\" so replace it with pair "\\"
		path = path.replaceAll("\\\\", "\\\\\\\\");

		Utils.replaceAll(prefs, "\\Q${DOWNLOADPATH}\\E", path);

		try {
			File log = SELENIUM_LOG == null ? new File(tmp, "selenium.log")
					: new File(SELENIUM_LOG);
			
			RemoteControlConfiguration config = new RemoteControlConfiguration();
			config.setLogOutFile(log);
			config.setUserExtensions(userExtensions);
			config.setFirefoxProfileTemplate(new File(System.getProperty("selenium.profile", profile.getAbsolutePath())));
			config.setPort(Integer.parseInt(SELENIUM_PORT));
			
			System.out.println("****** Configuration ***** ");
			System.out.println("Port : " + config.getPort());
			System.out.println("Profile : " + config.getFirefoxProfileTemplate());
			System.out.println("Log : " + config.getLogOutFile());
			System.out.println("************************** ");
			
			server = new SeleniumServer(config);
			server.boot();
			server.start();
		} catch (Exception e) {
			server = null;
			throw new RuntimeException(
					ERR_FAILED_START_SELENIUM_SERVER.getProblem(), e);
		}
	}

	public static WIBrowser getNewBrowser(String url) {
		return getNewBrowser(Utils.getURL(url, ERR_INVALID_URL2));		
	}
	
	public static WIBrowser getNewBrowser(URL aUrl) {
		// Start Selenium server only when first test called getNewBrowser()
		if (useEmbededSeleniumServer) {
			if (!isSeleniumRunning) {
				startEmbededSeleniumServer();
				isSeleniumRunning = true;
			}
		} else {
			// TODO Add port listening check
			// If Selenium is inaccessible print human readable error
		}

		URL baseUrl = Utils.getURL(aUrl.getProtocol(), aUrl.getHost(),
				aUrl.getPort(), "", ERR_SELENIUM_INVALID_URL2);

		// TODO Add new browser to collection to be able close all of them
		WIBrowser browser = new WIBrowser(getSeleniumURL().getHost(),
				getSeleniumURL().getPort(), SELENIUM_BROWSER,
				baseUrl.toString());
		// getOvmManagerBaseURL().toString());

		createdBrowsers.add(browser);

		// Since combination of Selenium and WebDriver is used it's unnecessary
		// to start browser because of WebDriver opens it by itself
		// Uncomment this code for debug purpose only
		// browser.start();

		// Default timeout is 10 seconds but sometimes it not enough (may be due to network issues)
		// so lets increase it up to 30 seconds
		browser.setTimeout(System.getProperty("selenium.timeout", "30000"));
		
		// Highlight all HTML elements during work to get visual control of
		// executed operations
		browser.setShouldHighlightElement(true);

		browser.windowMaximize();

		browser.open(aUrl);

		Text.autoLocale(browser);
		
		return browser;
	}

	public static void closeAll() {
		for (int i = 0; i < createdBrowsers.size(); i++) {
			try {
				createdBrowsers.get(i).close();
			} catch (Exception e) {
				log.error(ERR_FAILED_CLOSE_BROWSER.getProblem());
			} finally {
				createdBrowsers.remove(i--);
			}
		}
	}
	
	public static List<WIBrowser> getBrowsers() {
		return createdBrowsers;
	}

	private WIBrowserFactory() {
	}

	public static void main(String[] args) {
		startEmbededSeleniumServer();
	}

}
