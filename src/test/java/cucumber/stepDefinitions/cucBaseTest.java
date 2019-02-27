package cucumber.stepDefinitions;

import browsers.ChromeDriverSet;
import browsers.FireFoxDriverSet;
import cucumber.api.Result;
import cucumber.api.Scenario;
import interfaces.InterfaceSeleniumDriver;

public class cucBaseTest {

	public InterfaceSeleniumDriver driver;

	public void takeScreenOnFailure(Scenario cucScenario){
		System.out.print("SCENARIO " + cucScenario.getName());
		if(cucScenario.getStatus() == Result.Type.FAILED){
			System.out.println(" FAILED at lines " + cucScenario.getLines());
			driver.takeScreenShotBytes();
			System.out.println("Screenshot captured");
		}
		else
			System.out.println(" SUCCEED");
	}
	
	public void driverIni(String browserName) {
		if(browserName.contains("Chrome")) {
			this.driver = new ChromeDriverSet();
		}
		else if(browserName.contains("FireFox")){
			this.driver = new FireFoxDriverSet();
		}
		
		driver.driverInitialize();
		driver.browserManage().window().maximize();
	}
	
	public void afterClass() {
		if(driver != null)
		driver.quitBrowser();		
	}
}
