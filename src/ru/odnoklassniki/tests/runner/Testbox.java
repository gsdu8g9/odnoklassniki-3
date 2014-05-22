package ru.odnoklassniki.tests.runner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.testng.TestNG;

import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;

/**
 * Main framework class. TestNG wrapper to add extra functionality
 * 
 */
public class Testbox {

	private static File tmpFolder = new File("temp");

	/**
	 * Returns temporary framework folder. Create if not exists
	 * 
	 * @return folder
	 */
	public static File getTmpFolder() {
		if (!tmpFolder.exists()) {
			tmpFolder.mkdirs();
		}
		return tmpFolder;
	}

	public static void main(String[] args) throws IOException {
		Thread.setDefaultUncaughtExceptionHandler(new TestboxExceptionHandler());

		if (Arrays.asList(args).contains("-start-selenium")) {
			WIBrowserFactory.main(null);
			return;
		}

		TestNG.main(args);
	}

}
