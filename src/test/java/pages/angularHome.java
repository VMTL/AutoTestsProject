package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import interfaces.InterfaceSeleniumDriver;

public class angularHome extends BasePage{

	public angularHome(InterfaceSeleniumDriver driver) {
		super(driver);
	}	
	
	public WebElement fieldName() {
		return driver.findElementByXpath("//*[@class='ng-scope']//input[@type='text'][@ng-model='formData.name']");
				//(ByAngular.model("formData.name"));
	}
	
	public WebElement fieldEmail() {
		return driver.findElementByXpath("//*[@class='ng-scope']//input[@type='text'][@ng-model='formData.email']");
				//(ByAngular.model("formData.email"));
	}
	
	public WebElement ngBinding() {
		return driver.findElementByXpath("//*[@class='ng-binding']");
	}	

	public WebElement nextButton() {
		return driver.findElementByLocator(By.partialLinkText("Next Section"));
	}
	
	public WebElement xBox() {
		return driver.findElementByXpath("//*[@type='radio'][@value='xbox']");
	}
	
	public WebElement submitDanger() {
		return driver.findElementByXpath("//*[@class='btn btn-danger'][@type='submit']");
	}
}
