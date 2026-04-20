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
	
	//2. Preemptive authentication
	@Test
	public void verifyPreemptiveAuth()
	{
		
		given()
		    .auth().preemptive().basic("postman", "password")
		.when()
		    .get("https://postman-echo.com/basic-auth")
		.then()
		    .statusCode(200)
		    .body("https://postman-echo.com/basic-auth", equalTo(true))
		    .log().all();
	}
	
	//3. digest Authentication
	@Test
	public void verifyDigestAuth()
	{
		
		given()
		  .auth().digest("postman","password")
	    .when()
	      .get("https://postman-echo.com/basic-auth")
	    .then()
	      .statusCode(200)
	      .body("Authentication", equalTo(true))
	      .log().all();
	}
	
	//4. Bearer Token
	public void verifyTokenAuth()
	{
		
		String Token = "fgh_f3048fdnv087364e38e20yrhdd66986903832hd";
		
		given()
		    .header("Authorization" , "Bearer"+Token)
		.when()
		    .get("https://postman-echo.com/basic-auth")
		.then()
		    .statusCode(200)
		    .body("Authentication",equalTo(true))
		    .log().all();	
	}
	
	
	//5. API Key Authentication
	@Test
	public void verifyAPIkeyAuth()
	{
		
		given()
		  .queryParam("q", "Delhi")
		  .queryParam("appid", "fekdnfda87t98y302r43jbe0w7r23prpejjef")
		.when()
		  .get("https://api.openweathermap.org/data/2.5/weather")
		.then()
		  .statusCode(200)
		  .log().all();
		
	}
}
