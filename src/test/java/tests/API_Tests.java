package tests;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import helpers.Read_Excel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class API_Tests {

	private Map<String, String> headersMap;
	private Map<String, String> parametersMap;
	private Read_Excel readExcel;
	private static final String baseUri = "https://www.alphavantage.co/";
	
	@BeforeClass(description = "Reading data from excel to prepare API call")
	public void setUp() {
		//READ TEST CASES PARAMETERS FROM EXCEL FILE
		readExcel = new Read_Excel(System.getProperty("user.dir") + "\\resources\\Test_Cases.xlsx");
		
		// FETCH HEADERS AND PARAMETERS DATA FROM EXCEL SHEET AND PUT IT INTO THE HASHMAP
		headersMap = new HashMap<String, String>(readExcel.excelToMap("Header"));
		parametersMap = new HashMap<String, String>(readExcel.excelToMap("Parameters"));
	}
	
	@Test(description = "Checking Global Quote data")
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
		
		assertEquals(httpRequest.statusCode(), "200");
		assertEquals(httpRequest.getBody().jsonPath().getJsonObject("'Global Quote'.'01. symbol'"), "MSFT");
	}
}
