package Day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.lessThan;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpMethodDemo {

    @Test(enabled = false)
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
    	
    	HashMap<String , String> payload = new HashMap<>();
    	
    }
}
