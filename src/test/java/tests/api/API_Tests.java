package tests.api;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import application.Application;
import helpers.JSONparser;
import helpers.Read_Excel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class API_Tests extends AbstractTestNGSpringContextTests {

	private Map<String, String> headersMap;
	private Map<String, String> parametersMap;
	private Read_Excel readExcel;
	private static final String baseUri = "https://www.alphavantage.co/";
	private String baseUriRandom;
	
    @LocalServerPort
    private int randomServerPort;

    @PostConstruct
    public void postConstruct(){        
		this.readExcel = new Read_Excel(System.getProperty("user.dir") + "\\resources\\Test_Cases.xlsx");
		
		// FETCH HEADERS AND PARAMETERS DATA FROM EXCEL SHEET AND PUT IT INTO THE HASHMAP
		this.headersMap = new HashMap<String, String>(readExcel.excelToMap("Header"));
		this.parametersMap = new HashMap<String, String>(readExcel.excelToMap("Parameters"));
		
		this.baseUriRandom = "http://localhost:" + this.randomServerPort + "/api/products/";
	}
    
	@Test(description="Testing Global Quote data")
	public void alphaVantageMSFT(){
		Response httpRequest = RestAssured
				.given()
					.contentType("application/json")
					.headers(headersMap)
					.queryParams(parametersMap)
					.baseUri(baseUri)
					.basePath("query")
				.when()
					.get()
				.thenReturn();
		
		assertEquals(httpRequest.statusCode(), 200);
		assertEquals(httpRequest.getBody().jsonPath().getJsonObject("'Global Quote'.'01. symbol'"), "MSFT");
	}
	
	@Test(description="Testing product creation", dataProvider="products")
	public void createProductTest(String product) {
		Response httpRequest = RestAssured
				.given()
					.contentType("application/json")
					.baseUri(baseUriRandom)
					.body(product)
				.when()
					.post()
				.thenReturn();
		
		JsonObject json = new JSONparser().jsonObjFromString(product);
		
		assertEquals(httpRequest.statusCode(), 200);
		assertEquals(httpRequest.getBody().jsonPath().getJsonObject("name").toString(), json.get("name").getAsString());
	}
	@DataProvider(name="products")
    public Object[] dataProviderMethod() {
        return readExcel.getExcelDataSimpleArray("products");
    }
}
