package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetMavenProperties {
	
	private FileInputStream inputStream;
	private Properties mavenProperties;

	public String getProp(String varProperty) { 
		mavenProperties = new Properties();
		try {
			inputStream = new FileInputStream(System.getProperty("user.dir") + "resources\\maven.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Can't open maven.properties file");
			e.printStackTrace();
		}
		
		try {
			mavenProperties.load(inputStream);
		} catch (IOException e) {
			System.out.println("Can't load maven.properties file");
			e.printStackTrace();
		}
		
		return mavenProperties.getProperty(varProperty);
	}	
}
