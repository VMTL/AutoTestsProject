package interfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface InterfaceFindElement {
	List<WebElement> findElementsByLocator(By byLocator);
	WebElement findElementByLocator(By byLocator);
	WebElement findElementByXpath(String xPath);
}