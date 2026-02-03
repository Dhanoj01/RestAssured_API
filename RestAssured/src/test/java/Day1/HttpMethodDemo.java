package Day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

public class HttpMethodDemo {

	
	@Test
	public void getMethod()
	{
		
		
		given()
		 .header("User-Agent", "Mozilla/5.0")
         .header("Accept", "application/json")
		.when()
		    .get("https://reqres.in/api/users?page=2")
		.then()
		   .statusCode(200)
		   .log().body();
	}
}
