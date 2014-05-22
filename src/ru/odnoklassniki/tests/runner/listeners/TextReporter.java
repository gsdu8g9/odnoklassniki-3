package ru.odnoklassniki.tests.runner.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import ru.odnoklassniki.tests.common.Loggers;

public class TextReporter implements ITestListener, IInvokedMethodListener {

	private String getTestName(ITestResult result) {
		ITestNGMethod method = result.getMethod();
		return method.getTestClass().getName() + "." + method.getMethodName();
	}
	
	@Override
	public void onStart(ITestContext context) {
	}
	
	@Override
	public void onFinish(ITestContext context) {
	}
	
	@Override
	public void onTestStart(ITestResult result) { 		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(" FAILED : " + getTestName(result));
		Loggers.framework.error("====== " + getTestName(result) + " [FAILED] ======", result.getThrowable());
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

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult result) {
		if (null != result.getThrowable()) {
			Loggers.framework.error("====== " + getTestName(result) + " [FAILED] ======", result.getThrowable());
		}
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult result) {
		Loggers.framework.info("====== " + getTestName(result) + " ======");
	}

}