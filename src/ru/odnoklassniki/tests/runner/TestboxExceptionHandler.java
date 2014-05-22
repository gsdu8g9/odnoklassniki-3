package ru.odnoklassniki.tests.runner;

import java.lang.reflect.InvocationTargetException;

/**
 * Global framework exception handler
 * 
 */
class TestboxExceptionHandler implements Thread.UncaughtExceptionHandler {

	public void uncaughtException(Thread t, Throwable e) {
		// Some exceptions are wrapped into InvocationTargetException since
		// it was called by one-jar classes so skip it
		if (e instanceof InvocationTargetException) {
			uncaughtException(t, e.getCause());
			return;
		}
		// Exception in static section of any class produces
		// ExceptionInInitializerError
		// exception while real cause is deeper so skip it
		if (e instanceof ExceptionInInitializerError) {
			uncaughtException(t, e.getCause());
			return;
		}
		if (e instanceof TestboxException) {
			TestboxException te = (TestboxException) e;
			System.err.println("ERROR: " + te.getMessage());
			if (null != te.getCause()) {
				System.err.println("   CAUSE:");
				te.getCause().printStackTrace(System.err);
			}
			return;
		}
		System.err.println("UNHANDLED EXCEPTION");
		e.printStackTrace();
	}

}