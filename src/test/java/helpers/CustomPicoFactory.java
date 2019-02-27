package helpers;

import interfaces.InterfaceSeleniumDriver;
import cucumber.runtime.java.picocontainer.PicoFactory;

public class CustomPicoFactory extends PicoFactory {
	
    public CustomPicoFactory() {
       addClass(InterfaceSeleniumDriver.class);
    }
}