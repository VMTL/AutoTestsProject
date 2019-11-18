package browsers;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import Abstract.AbstractDriver;

public class IEdriverSet extends AbstractDriver {

	private static final String ieDriverPath = System.getProperty("user.dir") + "\\resources\\IEDriverServer.exe";
	private static InternetExplorerOptions ieOptions;
	private static DesiredCapabilities capabilities;

	@Override
	public void driverInitialize() {
    	System.setProperty("webdriver.ie.driver", ieDriverPath);
    	driver = new InternetExplorerDriver();
    	System.out.println("IE browser is open");
    	ieOptions = new InternetExplorerOptions();
    	capabilities = DesiredCapabilities.internetExplorer();
    	//To make IE run via Selenium WebDriver
    	capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
    }
		
	public void setProxy(Proxy proxy){
		ieOptions.setProxy(proxy);
	}

	@Override
	public void setHeadless() {		
	}
}