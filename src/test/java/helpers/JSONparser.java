package helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONparser {

	public JsonObject jsonObj;
	public JsonArray jsonArr;
	
	public void jsonObj(String jsonString) {
		this.jsonObj = new JsonParser().parse(jsonString).getAsJsonObject();
	}

	public void jsonArr(String jsonString) {
		try {
			this.jsonArr = new JsonParser().parse(jsonString).getAsJsonArray();
		}
		catch(java.lang.IllegalStateException e) {
			System.out.println("An element is Not a JSON array");
		}
	}
}