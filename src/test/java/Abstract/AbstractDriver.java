package Abstract;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.paulhammant.ngwebdriver.NgWebDriver;

import interfaces.InterfaceSeleniumDriver;
import io.qameta.allure.Attachment;

public abstract class AbstractDriver implements InterfaceSeleniumDriver {
	
	public WebDriver driver;
	
	public abstract void driverInitialize();
	public abstract void setProxy(Proxy proxy);
	
	public NgWebDriver ngDriverInitialize() {
		return new NgWebDriver((JavascriptExecutor) driver);
	}
	
	public void quitBrowser(){
    	if(driver != null) {
    		System.out.println("Closing a browser");
    		driver.quit();
    	}
    	else {System.out.println("Browser has been already closed");}
    }
	
	public Options browserManage() {
        return driver.manage();
	}
    
	public Navigation browserNavigate() {
    	return driver.navigate();
    }
	
	public void closeBrowserWindow() {
		driver.close();
	}
	
	public TargetLocator switchTo() {
    	return driver.switchTo();
    }
    
	public String getPageUrl() {
		return driver.getCurrentUrl();
    }
    
	public String getPageTitle() {
		return driver.getTitle();
    }
	
	public List<WebElement> findElementsByLocator(By byLocator){
		List<WebElement> listWebElement;
		try {
			listWebElement = driver.findElements(byLocator);
		}
		catch(ElementNotFoundException e) {
			driverWaitUntilVisible(waitInSeconds, byLocator);
			listWebElement = driver.findElements(byLocator);
		}
		javaScriptInvoke().executeScript("arguments[0].scrollIntoView();", listWebElement);
    	return listWebElement;    	
    }
    
	public WebElement findElementByLocator(By byLocator){
		WebElement webElement;
		try {
			webElement = driver.findElement(byLocator);
		}
		catch(ElementNotFoundException e) {
			driverWaitUntilVisible(waitInSeconds, byLocator);
			webElement = driver.findElement(byLocator);
		}
		javaScriptInvoke().executeScript("arguments[0].scrollIntoView();", webElement);
    	return webElement;
    }
    
	public WebElement findElementByXpath(String xPath){
		WebElement webElement;
		try {
			webElement = driver.findElement(By.xpath(xPath));
		}
		catch(ElementNotFoundException e) {
			driverWaitUntilVisible(waitInSeconds, By.xpath(xPath));
			webElement = findElementByLocator(By.xpath(xPath));
		}
		javaScriptInvoke().executeScript("arguments[0].scrollIntoView();", webElement);
    	return webElement;
    }
	
	public Timeouts implicitWait(int waitInSeconds) {
        return browserManage().timeouts().implicitlyWait(waitInSeconds, TimeUnit.SECONDS);
	}
	
	public Timeouts pageLoadWait(int waitInSeconds) {
        return browserManage().timeouts().pageLoadTimeout(waitInSeconds, TimeUnit.SECONDS);
	}
	
	public Timeouts asyncScriptWait(int waitInSeconds) {
        return browserManage().timeouts().setScriptTimeout(waitInSeconds, TimeUnit.SECONDS);
	}
	
	public void setWaitInSeconds(int waitInSecodns) {
		this.waitInSeconds = waitInSecodns;
	}

	public WebDriverWait driverWait(int waitInSeconds) {
		return new WebDriverWait(driver, waitInSeconds);
	}
	
	public void driverWaitUntilVisible(int waitInSeconds, By byLocator) {
		driverWait(waitInSeconds).until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
    
	public JavascriptExecutor javaScriptInvoke() {
		return (JavascriptExecutor) driver;
    }
	
	public Actions actionBuilder() {
		return new Actions(driver);
	}
	
	public File takeScreenShot() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	}

	@Attachment(value = "ScreenshotAttachment", type = "image/png")
	public byte[] takeScreenShotBytes() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}
	
	private int waitInSeconds;
	//private WebElement webElement;
}