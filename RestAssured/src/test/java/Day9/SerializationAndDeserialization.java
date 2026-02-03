package Day9;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class SerializationAndDeserialization {
	
	String stuId;
	
	@Test	public void testSerialization()
	{
		String courses[] = {"Selenium","java","Python"};
		
		Student st = new Student("Jhon","Delhi","1234567890",courses);
		
	stuId =	given()
		   .contentType("application/json")
		   .body(st)       //serialization happens
		.when()
		   .post("http://localhost:8000/data.json")
		.then()
		  .statusCode(200)
		  .log().body()
		  .extract().response().jsonPath().getString("id");
	
	}
	
	
	
	@Test(dependsOnMethods = "testSerialization")
	public void testDeserialization()
	{
		
		Response response =given()
		    .pathParam("id", stuId)
		.when()
		    .get("http://localhost:8000/data.json/{id}")
		.then()
		    .statusCode(200)
		    .extract().response();
		
		//Extract "id" separately from JSON response
		String extractedId = response.jsonPath().getString("id");
		
		//Deserialization - convert JSON to student Object
		Student stu = response.as(Student.class);
		
		//validation
		assertThat( stu.getName(), is("Jhon"));
	    assertThat(stu.getLocation(), is("Delhi"));
	    
		System.out.println("Student details : " + stu + extractedId);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Test(dependsOnMethods = "testSerialization")
	public void deleteStudent()
	{
		given()
		    .pathParam("id", stuId)
		.when()
		    .delete("http://localhost:8000/data.json/{id}")
		.then()
		     .statusCode(200);
	}

}
