package apitest.Firstapi;

import io.restassured.path.json.JsonPath;

public class Reusablemethods {

	
	public static JsonPath RawtoJson(String data) {
		JsonPath js  = new JsonPath(data);
		return js;
	}
}
