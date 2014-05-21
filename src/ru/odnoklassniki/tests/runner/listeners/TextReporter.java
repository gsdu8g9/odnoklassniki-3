package ru.odnoklassniki.tests.runner.listeners;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import ru.odnoklassniki.tests.common.Loggers;

public class TextReporter extends TestListenerAdapter {

	private String getTestName(ITestResult result) {
		ITestNGMethod method = result.getMethod();
		return method.getTestClass().getName() + "." + method.getMethodName();
	}
	
	@Override
	public void onTestStart(ITestResult result) { 		
		Loggers.framework.info("====== " + getTestName(result) + " ======");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(" FAILED : " + getTestName(result));
		Loggers.framework.info("====== " + getTestName(result) + " [FAILED] ======");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("SKIPPED : " + getTestName(result));
		Loggers.framework.info("====== " + getTestName(result) + " [SKIPPED] ======");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(" PASSED : " + getTestName(result));
		Loggers.framework.info("====== " + getTestName(result) + " [PASSED] ======");
	}

}