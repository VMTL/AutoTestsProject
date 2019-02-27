package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.API_Initialization;
import helpers.Read_Excel;
import net.minidev.json.JSONObject;

public class API_Tests {

	private Map<String, String> headersMap;
	private Map<String, String> parametersMap;
	private Read_Excel readExcel;
	private JSONObject jsonBody;
	private String baseUri;
	
	@BeforeClass(description = "Reading data from excel to prepare API call")
	public void setUp() {
		//READ TEST CASES PARAMETERS FROM EXCEL FILE
		readExcel = new Read_Excel(System.getProperty("user.dir") + "\\resources\\Test_Cases.xlsx");
		
		// TAKE HEADERS AND PARAMETERS DATA FROM EXCEL SHEET AND PUT IT INTO THE HASHMAP
		headersMap = new HashMap<String, String>(readExcel.excelToMap("Header"));
		parametersMap = new HashMap<String, String>(readExcel.excelToMap("Parameters"));
		
		//CREATE A NEW JSON AND READ A DATA WITH JASON FROM EXCEL SHEET
		jsonBody = new JSONObject();
		jsonBody = readExcel.exceSimpleArraylToJson("getByEmail");
		
		baseUri = "https://www.alphavantage.co/";
	}
	
	@Test(description = "Checking Global Quote data")
	public void alphaVantageMSFT(){
		API_Initialization apiIni = new API_Initialization(baseUri, "query", "", headersMap, parametersMap, jsonBody, "GET");
		apiIni.getResponse().then().statusCode(200).contentType("application/json").root("$.Global Quote");
	}
}