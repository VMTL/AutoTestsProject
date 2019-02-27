package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import interfaces.InterfaceSeleniumDriver;

public class InvestingHome extends BasePage {

	public InvestingHome(InterfaceSeleniumDriver driver){
		super(driver);
    }
	
	public WebElement inputSearch() {
		return driver.findElementByXpath("//*[@class='searchText arial_12 lightgrayFont js-main-search-bar']");
	}
	
	public WebElement selectSearchField() {
		driver.driverWaitUntilVisible(5, By.xpath("//*[@class='js-dropdown-text-input inputDropDown']"));
		return driver.findElementByXpath("//*[@class='js-dropdown-text-input inputDropDown']");
	}
	
	public void selectDropDownClick(String value) {
		driver.driverWaitUntilVisible(5, By.xpath("//*[@data-value='"+value+"']"));
		driver.findElementByXpath("//*[@data-value='"+value+"']").click();
	}
}