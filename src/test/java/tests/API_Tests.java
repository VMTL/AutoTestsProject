package tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import helpers.JSONparser;
import helpers.Read_Excel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class API_Tests extends AbstractTestNGSpringContextTests {

	private Map<String, String> headersMap;
	private Map<String, String> parametersMap;
	private Read_Excel readExcel;
	private static final String baseUri = "https://www.alphavantage.co/";
	private static final String baseUriLocal = "http://localhost:4448/api/products/";
	
	@BeforeClass(description = "Reading data from excel to prepare API call")
	public void setUp() {
		//READ TEST CASES PARAMETERS FROM EXCEL FILE
		this.readExcel = new Read_Excel(System.getProperty("user.dir") + "\\resources\\Test_Cases.xlsx");
		
		// FETCH HEADERS AND PARAMETERS DATA FROM EXCEL SHEET AND PUT IT INTO THE HASHMAP
		headersMap = new HashMap<String, String>(readExcel.excelToMap("Header"));
		parametersMap = new HashMap<String, String>(readExcel.excelToMap("Parameters"));
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
					.baseUri(baseUriLocal)
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
