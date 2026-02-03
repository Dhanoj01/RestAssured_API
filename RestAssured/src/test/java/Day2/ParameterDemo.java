package Day2;

import org.testng.annotations.Test;

public class ParameterDemo {
	
	@Test
	public void pathParam()
	{
		
		given()
		    .pathParam("country" , "India")
		.when()
		    .get("https://restcountries.com/v2/name/{country}")
		.then()
		    .statusCode(200)
		    .log().body();
	}

}
