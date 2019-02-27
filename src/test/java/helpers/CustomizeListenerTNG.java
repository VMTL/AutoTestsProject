package helpers;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import helpers.TimeFormats;

public class CustomizeListenerTNG implements ITestListener {

	private TimeFormats timeFormats = new TimeFormats();
		
	@Override
	public void onTestStart(ITestResult testResult) {
		System.out.println("TEST " + testResult.getName() + " STARTED at "
							+ timeFormats.milliSecToDate(testResult.getStartMillis()));
	}

	@Override
	public void onTestSuccess(ITestResult testResult) {
		System.out.println("TEST " + testResult.getName() + " SUCCEED at "
							+ timeFormats.milliSecToDate(testResult.getStartMillis()));
	}
	
	@Override
	public void onTestFailure(ITestResult testResult) {
		System.out.println("TEST " + testResult.getName() + " FAILED at "
							+ timeFormats.milliSecToDate(testResult.getStartMillis()));
		
		//This block is to modify Regular Java Assertion error
		
		Throwable throwable = testResult.getThrowable();
        String originalMessage = throwable.getMessage();
        if(originalMessage.contains("java.lang.AssertionError:")) {
        	String modifiedMessage = originalMessage.replace("java.lang.AssertionError:", "").trim();
        	Throwable throwableNew = new Throwable(modifiedMessage);
        	System.out.println("Assertion error: " + modifiedMessage);
        	try {
        		testResult.setThrowable(throwableNew);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
	}

	@Override
	public void onTestSkipped(ITestResult testResult) {
		System.out.println("TEST " + testResult.getName() + " SKIPPED");		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("TEST SUITE " + context.getName() + " STARTED at " + context.getStartDate());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("TEST SUITE " + context.getName() + " FINISHED at " + context.getEndDate());
	}
}
