package browsers;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Abstract.AbstractDriver;

public class ChromeDriverSet extends AbstractDriver {

	private static final String chromeDriverPath = System.getProperty("user.dir") + "\\resources\\chromedriver.exe";
	public ChromeOptions chromeOptions;
	private static final List<String> listArgs = Arrays.asList("--headless", "--disable-gpu", "--no-sandbox",
			"--enable-logging", "--window-size=1920,1080");
	
	public ChromeDriverSet() {
    	this.chromeOptions = new ChromeOptions();
	}

	@Override
	public void driverInitialize() {
    	System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    	driver = new ChromeDriver(this.chromeOptions);
    	System.out.println("Chrome browser is open");
    }
	
	@Override
	public void setHeadless() {
		this.chromeOptions.addArguments(listArgs);
	}
	
	@Override
	public void setProxy(Proxy proxy){
		chromeOptions.setProxy(proxy);
	}

	public void setAcceptInsecureCert() {
		chromeOptions.setAcceptInsecureCerts(true);
	}
}