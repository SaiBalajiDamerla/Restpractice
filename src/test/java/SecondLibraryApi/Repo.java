package SecondLibraryApi;

public class Repo {

	public static String Postbody(String isbnValue, int aisleValue ) {
		return " {\n"
				+ "\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\""+isbnValue+"\",\n"
				+ "\"aisle\":\""+aisleValue+"\",\n"
				+ "\"author\":\"John foe\"\n"
				+ "}" ;
	}
}
