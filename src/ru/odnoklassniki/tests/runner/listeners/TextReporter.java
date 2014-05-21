package ru.odnoklassniki.tests.runner.listeners;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TextReporter extends TestListenerAdapter {

	private String getTestName(ITestResult result) {
		ITestNGMethod method = result.getMethod();
		return method.getTestClass().getName() + "." + method.getMethodName();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(getTestName(result) + " FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(getTestName(result) + " SKIPPED");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(getTestName(result) + " PASSED");
	}

}