package browsers;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import Abstract.AbstractDriver;

public class FireFoxDriverSet extends AbstractDriver {

	private static final String ffDdriverPath = System.getProperty("user.dir") + "\\resources\\geckodriver.exe";
	private static FirefoxOptions ffOptions;
	
	@Override
	public void driverInitialize() {
		System.setProperty("webdriver.gecko.driver", ffDdriverPath);
		FirefoxOptions firefoxOptions = new FirefoxOptions();
	    firefoxOptions.setCapability("marionette", true);
		driver = new FirefoxDriver(firefoxOptions);
    	System.out.println("FF browser is open");
	}
	
	public void setHeadless(){
		ffOptions.setHeadless(true);
	}
	
	public void setAcceptInsecureCert(){
		ffOptions.setAcceptInsecureCerts(true);
	}
	@Override
	public void setProxy(Proxy proxy){
		ffOptions.setProxy(proxy);
	}
}