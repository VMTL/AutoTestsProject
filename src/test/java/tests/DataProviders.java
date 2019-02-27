package tests;

import org.testng.annotations.DataProvider;

import browsers.ChromeDriverSet;
import browsers.FireFoxDriverSet;
import browsers.IEdriverSet;

public class DataProviders {
	
	private static String browsersMvn = System.getProperty("browsersMvn");

	@DataProvider(name = "factoryBrowser")
	public static Object[] factoryDataProvider() {
		System.out.println(browsersMvn);
		String[] browsers = browsersMvn.split(",");
		Object[] obj = new Object[browsers.length];
		for(int i = 0; i < obj.length; i++) {
			if(browsers[i].equals("Chrome"))
				obj[i] = new ChromeDriverSet();
			if(browsers[i].equals("FF"))
				obj[i] = new FireFoxDriverSet();
			if(browsers[i].equals("IE"))
				obj[i] = new IEdriverSet();
		}
		return obj;
	}
}
