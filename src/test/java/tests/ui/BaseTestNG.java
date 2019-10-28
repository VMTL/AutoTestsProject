package tests.ui;

import org.testng.ITestResult;

import interfaces.InterfaceSeleniumDriver;

public class BaseTestNG {

	private InterfaceSeleniumDriver driver;

	public BaseTestNG(InterfaceSeleniumDriver driver) {
		this.driver = driver;
	}
	
	public void setUp() {
		driver.driverInitialize();
		//driver.browserManage().window().maximize();
	}
	
	public void tearDown() {
		driver.quitBrowser();
	}
	
	public void takeScreenOnFailure(ITestResult testResult){
		System.out.print("TEST Case " + testResult.getName());
		if(testResult.getStatus() == ITestResult.FAILURE){
			System.out.println(" FAILED at lines " + testResult.getTestName());
			driver.takeScreenShotBytes();
			System.out.println("Screenshot captured");
		}
		else
			System.out.println(" SUCCEED");
	}
}