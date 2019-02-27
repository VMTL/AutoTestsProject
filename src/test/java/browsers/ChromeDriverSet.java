package browsers;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Abstract.AbstractDriver;

public class ChromeDriverSet extends AbstractDriver {

	private static final String chromeDriverPath = System.getProperty("user.dir") + "\\resources\\chromedriver.exe";
	private static ChromeOptions chromeOptions;

	@Override
	public void driverInitialize() {
    	System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    	driver = new ChromeDriver();
    	System.out.println("Chrome browser is open");
    	chromeOptions = new ChromeOptions();
    }
	
	@Override
	public void setProxy(Proxy proxy){
		chromeOptions.setProxy(proxy);
	}

	public void setHeadless() {
		chromeOptions.setHeadless(true);		
	}

	public void setAcceptInsecureCert() {
		chromeOptions.setAcceptInsecureCerts(true);
	}
}