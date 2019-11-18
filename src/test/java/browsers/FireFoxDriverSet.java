package browsers;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import Abstract.AbstractDriver;

public class FireFoxDriverSet extends AbstractDriver {

	private static final String ffDdriverPath = System.getProperty("user.dir") + "\\resources\\geckodriver.exe";
	private FirefoxOptions firefoxOptions;
	private static final List<String> listArgs = Arrays.asList("--headless", "--disable-gpu", "--no-sandbox",
																"--enable-logging", "--window-size=1920,1080");
	
	public FireFoxDriverSet() {
		this.firefoxOptions = new FirefoxOptions();
	    firefoxOptions.setCapability("marionette", true);		
	}
	
	@Override
	public void driverInitialize() {
		System.setProperty("webdriver.gecko.driver", ffDdriverPath);
		driver = new FirefoxDriver(firefoxOptions);
    	System.out.println("FF browser is open");
	}

	@Override
	public void setHeadless() {
		firefoxOptions.addArguments(listArgs);
	}
	
	public void setAcceptInsecureCert(){
		firefoxOptions.setAcceptInsecureCerts(true);
	}
	@Override
	public void setProxy(Proxy proxy){
		firefoxOptions.setProxy(proxy);
	}
}