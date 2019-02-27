package interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface InterfaceDriverWait {
	Timeouts implicitWait(int waitInSeconds);
	Timeouts pageLoadWait(int waitInSeconds);
	Timeouts asyncScriptWait(int waitInSeconds);
	WebDriverWait driverWait(int waitInSeconds);
    void driverWaitUntilVisible(int secondsWait, By byLocator);
    void setWaitInSeconds(int waitInSecodns);
}