package apitest.Firstapi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.asserts.*;

import static  io.restassured.RestAssured.*;
import  static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RahulShettybasicAPI {

	public static void main(String[] args) throws FileNotFoundException {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		PrintStream log =new PrintStream(new FileOutputStream("logging1.txt"));
		RequestSpecification  sp = new RequestSpecBuilder().addQueryParam("key", "qaclick123").addHeader("Content-Type","application/json") .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		
		String Bodyresponse = given().spec(sp).body(Data.URI()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.extract().body().asString();
		System.out.println("This Post body response "+Bodyresponse);
		
		JsonPath js = new JsonPath(Bodyresponse);
		String place_id = js.getString("place_id");
		
		
		GetRequest(place_id);
		String newaddesss = "2035 Vichy Rd ";
		PutRequest(place_id, newaddesss);
		GetRequest(place_id , newaddesss);
		
		DeleteRequest(place_id);
		GetRequest(place_id);
	}
public static  void GetRequest(String place_id) {
	String GetBodyResponse = given().queryParam("key", "qaclick123").queryParam("place_id", place_id).when().get("/maps/api/place/get/json").then().extract().body().asString();
	System.out.println("This Get body response "+GetBodyResponse);
	JsonPath js  = new JsonPath(GetBodyResponse);
	js.get("address");
}
public static  void GetRequest(String place_id, String newaddress) {
	String GetBodyResponse = given().queryParam("key", "qaclick123").queryParam("place_id", place_id).when().get("/maps/api/place/get/json").then().extract().body().asString();
	System.out.println("This Get body response "+GetBodyResponse);
	//Reusablemethods a = new Reusablemethods();
	JsonPath js = Reusablemethods.RawtoJson(GetBodyResponse);
	String ActualAdd=js.get("address");
	Assert.assertEquals(ActualAdd, newaddress);
}
 
public static void PutRequest(String place_id , String newaddress) {
	
	String UpdatedbodyResponse = given().queryParam("key","qaclick123").body("{\n"
			+ "\"place_id\":\""+place_id+"\",\n"
			+ "\"address\":\""+newaddress+"\",\n"
			+ "\"key\":\"qaclick123\"\n"
			+ "\n"
			+ "}").when().put("/maps/api/place/update/json").then().extract().body().asString();
	
		System.out.println("This Put body response "+UpdatedbodyResponse);
	
	
}
public static void DeleteRequest(String place_id) {
	
	String DelBodyResponse = given().queryParam("key","qaclick123").body("{\n"
			+ "    \"place_id\": \""+place_id+"\"\n"
			+ "\n"
			+ "}\n"
			+ "").when().delete("/maps/api/place/delete/json").then().extract().body().asString();
	System.out.println("This Deleter body response "+DelBodyResponse);
	
	
}
	
}
