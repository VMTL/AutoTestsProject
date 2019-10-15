package helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONparser {

	private JsonArray jsonArr;
	private JsonObject jsonObject;
	
	public JSONparser() {
		this.jsonObject = new JsonObject();
	}
	
	public JsonObject getJsonObject() {
		return this.jsonObject;
	}
	
	public JsonObject jsonObjFromString(String jsonString) {
		return new JsonParser().parse(jsonString).getAsJsonObject();
	}

	public JsonArray jsonArrFromString(String jsonString) {
		try {
			jsonArr = new JsonParser().parse(jsonString).getAsJsonArray();
		}
		catch(java.lang.IllegalStateException e) {
			System.out.println("An element is Not a JSON array");
		}
		return jsonArr;
	}
}