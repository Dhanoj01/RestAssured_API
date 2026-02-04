package Day2;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import org.testng.annotations.Test;

public class ParameterDemo {
	
	@Test
	public void pathParam()
	{
		
		given()
		    .pathParam("country" , "India")
		.when()
		    .get("https://restcountries.com/v2/name/{country}")  //https://restcountries.com/v2/name/India
		.then()
		    .statusCode(200)
		    .log().body();
	}
	
	
	@Test
	public void queryParamm()
	{
		
		given()
		    .queryParam("page","2")
		    .queryParam("id", "5")
		.when()
		    .get("https://reqres.in/api/users")     //https://reqres.in/api/users?page=2&id=2
		.then()
		    .statusCode(200)
		    .log().body();
	}

}
