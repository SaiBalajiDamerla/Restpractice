package SecondLibraryApi;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DataproviderData {

	
public void PostRequest(String isbnValue, int aisleValue) {
	
		String isbnValue1 = isbnValue;
		int aisleValue1= aisleValue;
		Postmethoid(isbnValue1 ,aisleValue1);
}

	
	@Test	(dataProvider="bookdata")
public static void Postmethoid(String isbnValue, int aisleValue ) {
		RestAssured.baseURI = "http://216.10.245.166";
String PostBody	= given().body(Repo.Postbody(isbnValue,aisleValue)).when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().body().asString();	
	JsonPath js = new JsonPath(PostBody);	
	System.out.println(js.getString("Msg"));
	String bookId= js.getString("ID");

	String ExpectedId = (isbnValue+aisleValue);
	System.out.println(bookId);
	System.out.println(ExpectedId);
	Assert.assertEquals(ExpectedId, bookId);
}
	@Test 	(dataProvider="bookdata")
	public void deletemethod(String data1, int data2) {
		String DeleteID = data1+data2;
		RestAssured.baseURI = "http://216.10.245.166";
		String DeleteBody = given().body("{\n"
				+ " \n"
				+ "\"ID\" : \""+DeleteID+"\"\n"
				+ " \n"
				+ "} \n"
				+ "").when().delete("/Library/DeleteBook.php").then().assertThat().statusCode(200).extract().body().asString();
	JsonPath js2  = new JsonPath (DeleteBody);
System.out.println(DeleteID+" "+	js2.getString("msg"));
	}
	
@DataProvider(name = "bookdata")
public Object [][] data (){
	
	return new Object [][]  {{"qwrweq",1234},{"asdf",9423},{"zxcv",5903}};
}
}
