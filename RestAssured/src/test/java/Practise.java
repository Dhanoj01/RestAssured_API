import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;


public class Practise{
	
	@Test
	public void postRequest()
	{
		
		String payload  = "";
		
		given()
		  .contentType("application/json")
		  .body(payload)
	    .when()
	        .post("")
	    .then()
	        .statusCode(201)
	        .body("name", equalTo("Dhanoj"))
	        .body("id" , equalTo("1"))
	        .log().all();
	}
	
}