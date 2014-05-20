package ru.odnoklassniki.tests.runner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.testng.TestNG;

import ru.odnoklassniki.tests.ui.api.WIBrowserFactory;


public class Testbox {

	static class Handler implements Thread.UncaughtExceptionHandler {
		
		public void uncaughtException(Thread t, Throwable e) {
			// Some exceptions are wrapped into InvocationTargetException since
			// it was called by one-jar classes so skip it
			if (e instanceof InvocationTargetException) {
				uncaughtException(t, e.getCause());
				return;
			}
			// Exception in static section of any class produces ExceptionInInitializerError
			// exception while real cause is deeper so skip it
			if (e instanceof ExceptionInInitializerError) {
				uncaughtException(t, e.getCause());
				return;
			} 
			if (e instanceof TestboxException) {
				TestboxException te = (TestboxException)e;				
				System.err.println("   ERROR: " + te.getProblem());
				System.err.println("SOLUTION: " + (te.getSolution() == null ? "N/A" : te.getSolution()));
				if (te.getCause() != null) {
					System.err.println("   CAUSE:");
					te.getCause().printStackTrace(System.err);
				}
				return;
			} 
			System.err.println("UNHANDLED EXCEPTION");
			e.printStackTrace();
		}
		
	}
	
	private static File tmpFolder = new File("temp");
	
	public static File getTmpFolder() {
		if (!tmpFolder.exists()) {
			tmpFolder.mkdirs();
		}
		return tmpFolder;
	}

	public static void main(String[] args) throws IOException {
		Thread.setDefaultUncaughtExceptionHandler(new Handler());
		
		if (Arrays.asList(args).contains("-start-selenium")) {
			WIBrowserFactory.main(null);
			return;
		}

		TestNG.main(args);
	}
	

}
