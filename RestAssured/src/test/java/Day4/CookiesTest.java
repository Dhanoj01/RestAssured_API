package Day4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.response.Response;

public class CookiesTest {

	@Test
	void testCookiesInResponse()
	{
		
		Response response = given()
		.when()
		    .get("https://www.google.com/")
		.then()
		    .statusCode(200)
		    .log().cookies()
		    .cookies("AEC" , notNullValue())
		    .extract().response();
		
      
		//extract specific cookie
	 String cookieValue = response.getCookie("AEC");
	 System.out.println("AEC : "+cookieValue);
	 
	 
	 //extract all the cookie
     Map<String, String>allCookie = response.getCookies();    //map is return type os getCookies()
     System.out.println("All Cookies : " + allCookie);
	 
	 
	 //print cookie and there values using loop
     for(String key : allCookie.keySet())
     {
    	 System.out.println(key + " : "+ allCookie.get(key));
     }
     
     
     //get detailed info of any cookie
     Cookie cookie_info = response.getDetailedCookie("AEC");
     
     System.out.println(cookie_info.hasExpiryDate());
     System.out.println(cookie_info.hasValue());
     System.out.println(cookie_info.getValue());
     System.out.println(cookie_info.isSecured());
     System.out.println(cookie_info.getExpiryDate());
     
   
     
	}
}
