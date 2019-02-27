package interfaces;

import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;

import com.paulhammant.ngwebdriver.NgWebDriver;

public interface InterfaceBrowser {
	void driverInitialize();
    void quitBrowser();//Close all windows
    void closeBrowserWindow();//Close a window with a focus
    NgWebDriver ngDriverInitialize();
    TargetLocator switchTo();
    Options browserManage();
}