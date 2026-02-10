package Day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpMethodDemo {

	int userId;
	
    @Test(enabled = true)
    public void getMethod() {

        given()
        .when()
           // .get("https://reqres.in/api/users?page=2")
              .get("https://jsonplaceholder.typicode.com/users")       //we used this link because that was not working
        .then()
            .statusCode(200)
            .time(lessThan(2000L))
            .header("Content-Type", containsString("application/json"))
           // .body("page", equalTo(2))
           // .body("data.email", everyItem(containsString("@")))
            .body("email", everyItem(containsString("@")))
            .log().body();
    }
    
    
    @Test(priority=2 , enabled = true)
    public void postMethod()
    {
    	
    	HashMap<String , String> data = new HashMap<>();
    	
    	data.put("name", "pavan");
    	data.put("job", "trainer");
    	
    userId=	given()
    	   .contentType("application/json")
    	   .body(data)
    	.when()
    	   .post("https://jsonplaceholder.typicode.com/users") 
    	.then()
    	   .statusCode(201)
    	   .header("Content-Type", containsString("application/json"))
    	   .body("name", equalTo("pavan"))
    	   .body("job", equalTo("trainer"))
    	   .log().all()
    	   .extract().jsonPath().getInt("id");
    	
    	
    }
    
    @Test(priority=3 , enabled = true)
    public void putMethod()
    {
    	
    	HashMap<String , String> data = new HashMap<>();
    	
    	data.put("name", "dhanoj");
    	data.put("job", "engineer");
    	
        	given()
    	   .contentType("application/json")
    	   .pathParam("userId", userId)
    	   .body(data)
    	.when()
    	   .put("https://jsonplaceholder.typicode.com/users/{userId}") 
    	.then()
    	   .statusCode(200)
    	   .header("Content-Type", containsString("application/json"))
    	   .body("name", equalTo("dhanoj"))
    	   .body("job", equalTo("engineer"))
    	   .log().all();
    	   
    }
    
    @Test(priority=4 , enabled = true)
    public void deleteMethod()
    {
    	
        given() 
    	   .pathParam("userId", userId) 
    	.when()
    	   .delete("https://jsonplaceholder.typicode.com/users/{userId}") 
    	.then()
    	   .statusCode(200);
    	
    	   
    }
}
