package api;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.ConnectionConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

public class API_Initialization {
	
	private RequestSpecification httpRequest;
	private Response response;
	
	public API_Initialization() {
		this.httpRequest = RestAssured.given();
	}
	
	public Response getResponse() {
		return this.response;
	}
	
	public API_Initialization(String baseUri, String basePath, String targetUri, Map<String, String> headersMap,
							Map<String, String> parametersMap, JSONObject jsonBody, String methodType) {
		
		httpRequest = RestAssured.given();
		httpRequest.config(RestAssured.config = RestAssured.config().connectionConfig(ConnectionConfig.connectionConfig()
				.closeIdleConnectionsAfterEachResponseAfter(3000, TimeUnit.MILLISECONDS)))
			.body(jsonBody)
			.baseUri(baseUri)
			.basePath(basePath)
			.headers(headersMap)
			.queryParams(parametersMap)
		;
		switch(methodType) {
			case "POST" : this.response = httpRequest.post(targetUri);
			break;
			case "PUT" : this.response = httpRequest.put(targetUri);
			break;
			case "GET" : this.response = httpRequest.get(targetUri);
			break;
			case "OPTIONS" : this.response = httpRequest.options(targetUri);
			break;
			case "DELETE" : this.response = httpRequest.delete(targetUri);
			break;
			case "PATCH" : this.response = httpRequest.patch(targetUri);
			break;
		}
	}
}