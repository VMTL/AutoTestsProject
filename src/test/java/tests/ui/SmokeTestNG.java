package tests.ui;

import org.testng.annotations.Test;

import interfaces.InterfaceSeleniumDriver;
import pages.InvestingHome;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.ITestResult;

// RUNNING AS A SUITE. IF RUNNING AS A TEST THEN UNCOMMENT THE @LISTENER
//@Listeners(helpers.CustomizeListenerTNG.class)

public class SmokeTestNG extends BaseTestNG{
	
	private InvestingHome homePage;

	@Factory(dataProvider = "factoryBrowser", dataProviderClass = tests.ui.DataProviders.class)
	private SmokeTestNG(InterfaceSeleniumDriver driver) {
		super(driver);
		homePage = new InvestingHome(driver);
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

	@Test(description = "Checking HTTPS protocol redirection")
	private void checkHttps() {
		homePage.driver.browserNavigate().to("http://ca.investing.com");
		assertTrue(homePage.driver.getPageUrl().startsWith("https://"));
	}
	
	@DataProvider(name = "searchDropDownListProvider")
	private String[] searchDropDownList() {
		return new String[] {"indice", "fund", "currency"};
	}
	
	@Test(description = "Check name field input", dataProvider = "searchDropDownListProvider")
	private void searchDropDownListTest(String searchList) {
		homePage.driver.browserNavigate().to("http://ca.investing.com");
		homePage.inputSearch().sendKeys("euro swiss");
		homePage.selectSearchField().click();
		homePage.selectDropDownClick(searchList);
		assertTrue(homePage.driver.findElementByLocator(By.xpath("//*[@class='js-scrollable-results-wrapper newResultsContainer']")).isDisplayed());
	}  
}