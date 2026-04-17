package Day3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


public class AuthenticationTest {

	//Basic Authentication
	@Test
	public void verifyBasicAuth()
	{
		
		given()
		  .auth().basic("postman","password")
		.when()
		  .get("https://postman-echo.com/basic-auth")
	    .then()
	      .statusCode(200)
	      .body("Authentocation" , equalTo(true))
	      .log().body();
		
		
	     
	}
}
