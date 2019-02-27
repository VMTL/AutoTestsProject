package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.paulhammant.ngwebdriver.NgWebDriver;

import interfaces.InterfaceSeleniumDriver;

public class BasePage {
	
	public InterfaceSeleniumDriver driver;
	public NgWebDriver ngDriver;

    public BasePage(InterfaceSeleniumDriver driver){
        this.driver = driver;
        this.ngDriver = driver.ngDriverInitialize();
    }
    
    public InterfaceSeleniumDriver getDriver(){
    	return driver;
    }
    
	public String switchToAlert(){
		driver.switchTo().alert();
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
    	return alertMessage;
    }
	
	public Select selectDropDown(WebElement webElement) {
		return new Select(webElement);
	}
	
	public Object[][] dynamicTable(WebElement dynamicTable) {
    	//Creating a list of all "<tr>" elements inside the table. It will be the first array (rows)
    	List<WebElement> rowsTable = dynamicTable.findElements(By.tagName("tr"));
    	int rowsCount = rowsTable.size();
    	
	    String[][] dynamicTableArray = new String[rowsCount][];	    
	    
    	for (int row = 0; row < rowsCount; row++) {
    		//Creating a list of all "<td>" and "<th>" elements. It will be the second array (columns)
    		List<WebElement> Columns_row = rowsTable.get(row).findElements(By.xpath("./*"));//By.tagName("th"));
    		int columns_count = Columns_row.size();
    		dynamicTableArray[row] = new String[columns_count];
    		for (int column = 0; column < columns_count; column++) {
     	       //Retrieving a text from a cell
     	       String celtext = Columns_row.get(column).getText();
     	       dynamicTableArray[row][column] = celtext;
     	    }
    	}
		return dynamicTableArray;
    }
	
	public void openNewTab() {
		 //Open a new tab using CTRL + t. Doesn't work with headless browsers
		driver.findElementByLocator(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		System.out.println("A new tab is open");
	}
}