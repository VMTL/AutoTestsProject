package interfaces;

import org.openqa.selenium.WebDriver.Navigation;

public interface InterfaceNavigation {
	Navigation browserNavigate();
	String getPageTitle();
	String getPageUrl();
}