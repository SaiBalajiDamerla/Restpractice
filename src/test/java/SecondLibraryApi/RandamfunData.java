package SecondLibraryApi;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import  static io.restassured.RestAssured .*;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RandamfunData {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String isbnValue = RandomString(4);
		int asilevalue = Randomint();
		Postmethoid(isbnValue,asilevalue);
			
	}
public static int Randomint() {
	Random r = new Random();
	int randomnum = r.nextInt(1000);
		return randomnum;
	}
public static String RandomString(int length) {
	String charac = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 
	int chrac_lenght = charac.length();
	StringBuilder randomstring = new StringBuilder(length);
		Random r = new Random();
		for (int i =0; i<= length ;i++) {
		int randomindex =r.nextInt(chrac_lenght);
		char randomcharacter = charac.charAt(randomindex);
		randomstring.append(randomcharacter);}
	//int randomnum = r.ne;
		String finalRandomString = randomstring.toString();
		return finalRandomString;
	}
	public static void Postmethoid(String isbnValue, int aisleValue ) {
		
	String PostBody	= given().body(Repo.Postbody(isbnValue,aisleValue)).when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().body().asString();
		
		JsonPath js = new JsonPath(PostBody);
		System.out.println(js.getString("Msg"));
		String bookId= js.getString("ID");
	
		String ExpectedId = (isbnValue+aisleValue);
		System.out.println(bookId);
		System.out.println(ExpectedId);
		Assert.assertEquals(ExpectedId, bookId);
		
		
	}

}
