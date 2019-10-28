package tests.ui;

import static org.testng.Assert.assertEquals;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import interfaces.InterfaceSeleniumDriver;
import pages.angularHome;
import helpers.JSONparser;

public class angularTest extends BaseTestNG{
	
	private angularHome angPage;

	@Factory(dataProvider = "factoryBrowser", dataProviderClass = tests.ui.DataProviders.class)
	public angularTest(InterfaceSeleniumDriver driver) {
		super(driver);
		angPage = new angularHome(driver);
		driver.setWaitInSeconds(5);
	}
	
	@BeforeClass(description = "Opening a browser", alwaysRun = true)
	private void beforeClass() {
		setUp();
	}

	@AfterClass(description = "Closing a browser", alwaysRun = true)
	private void afterClass() {
		tearDown();
	}
	
	@AfterMethod(description = "Take a screenshot if a test is failed")
	public void afterMethod(ITestResult testResult){
		takeScreenOnFailure(testResult);
	}
	
	@Test(description = "Profile test")
	public void profileTest() {
		angPage.driver.browserNavigate().to("http://www.way2automation.com/angularjs-protractor/multiform/#/form/profile");
		angPage.driver.pageLoadWait(10);
		angPage.driver.implicitWait(10);
		angPage.fieldName().sendKeys("nameTest");
		angPage.fieldEmail().sendKeys("email@gmail.com");
		angPage.driver.asyncScriptWait(10);
		String jsonBindingString = angPage.ngBinding().getText();
		JSONparser jsParse = new JSONparser();
		jsParse.jsonObjFromString(jsonBindingString);
		assertEquals(jsParse.getJsonObject().get("name").getAsString(), "nameTest");
		assertEquals(jsParse.getJsonObject().get("email").getAsString(), "email@gmail.com");
	}
	
	@Test(description = "Interest test", dependsOnMethods = {"profileTest"})
	public void interestTest() {
		angPage.nextButton().click();
		angPage.driver.pageLoadWait(10);
		angPage.driver.asyncScriptWait(10);
		//angPage.driver.javaScriptInvoke().executeScript("document.querySelector(arguments[0]).click();", angPage.xBox());
		angPage.driver.javaScriptInvoke().executeScript("arguments[0].click();", angPage.xBox());
		angPage.driver.asyncScriptWait(10);
		String jsonBindingString = angPage.ngBinding().getText();
		JSONparser jsParse = new JSONparser();
		jsParse.jsonObjFromString(jsonBindingString);
		assertEquals(jsParse.getJsonObject().get("type").getAsString(), "xbox");
	}
	
	@Test(description = "Payment test", dependsOnMethods = {"interestTest"})
	public void paymentTest() {
		angPage.nextButton().click();
		angPage.driver.pageLoadWait(10);
		angPage.driver.asyncScriptWait(10);
		angPage.driver.javaScriptInvoke().executeScript("arguments[0].click();", angPage.submitDanger());
		angPage.driver.asyncScriptWait(10);
		assertEquals(angPage.switchToAlert(), "awesome!");
	}
}