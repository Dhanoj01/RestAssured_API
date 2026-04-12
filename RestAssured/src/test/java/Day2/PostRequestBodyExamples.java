package Day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.HashMap;

import org.json.JSONObject;

public class PostRequestBodyExamples {
	
	//1. create post request body using HashMap   ------------ small set of data
	//2. create post request body by org.json     ------------ small set of data 
	//3. Using POJO class (plain old java object) ------------ huge set of data 
	//4. create post request body using external json file --------- dont want to hard code the post data (request body)

	
	
	//1..
	public void createStudentUsingMap()
	{
		
		HashMap<String, Object> data = new HashMap<>();
		
		data.put("name","Vikash");
		data.put("location", "Pune");
		data.put("phone", "12345");
		
		String courses[] = {"c" , "c++"};
		
		data.put("courses", courses);
		
		int id =
		given()
		  .contentType("application/json")
		  .body(data)
		.when()
		    .post("http://localhost:3000/students")
		.then()
		    .statusCode(201)
		    .header("Content-Type" , "application/json")
		    .body("name", equalTo("Vikash"))
		    .body("courses[0]", equalTo("c"))
		    .time(lessThan(2000L))
		    .log().all()
		.extract()
		    .jsonPath()
		    .getInt("id");
		    
		    
	}
	
	
	//2.
	public void createStudentUsingJsonLibrary()
	{
		
		JSONObject data = new JSONObject();
		
		
		
		data.put("name","Vikash");
		data.put("location", "Pune");
		data.put("phone", "12345");
		
		String courses[] = {"c" , "c++"};
		
		data.put("courses", courses);
		
		int id =
		given()
		  .contentType("application/json")
		  .body(data.toString())               //only diff is to convert to string first and then pass
		.when()
		    .post("http://localhost:3000/students")
		.then()
		    .statusCode(201)
		    .header("Content-Type" , "application/json")
		    .body("name", equalTo("Vikash"))
		    .body("courses[0]", equalTo("c"))
		    .time(lessThan(2000L))
		    .log().all()
		.extract()
		    .jsonPath()
		    .getInt("id");
		    
		    
	}
	
	
	package Day2;

	import static io.restassured.RestAssured.given;
	import static org.hamcrest.Matchers.equalTo;
	import static org.hamcrest.Matchers.lessThan;

	import java.util.HashMap;

	import org.json.JSONObject;

	public class PostRequestBodyExamples {
		
		//1. create post request body using HashMap   ------------ small set of data
		//2. create post request body by org.json     ------------ small set of data 
		//3. Using POJO class (plain old java object) ------------ huge set of data 
		//4. create post request body using external json file --------- dont want to hard code the post data (request body)

		
		
		//1..
		public void createStudentUsingMap()
		{
			
			HashMap<String, Object> data = new HashMap<>();
			
			data.put("name","Vikash");
			data.put("location", "Pune");
			data.put("phone", "12345");
			
			String courses[] = {"c" , "c++"};
			
			data.put("courses", courses);
			
			int id =
			given()
			  .contentType("application/json")
			  .body(data)
			.when()
			    .post("http://localhost:3000/students")
			.then()
			    .statusCode(201)
			    .header("Content-Type" , "application/json")
			    .body("name", equalTo("Vikash"))
			    .body("courses[0]", equalTo("c"))
			    .time(lessThan(2000L))
			    .log().all()
			.extract()
			    .jsonPath()
			    .getInt("id");
			    
			    
		}
		
		
		//2.
		public void createStudentUsingJsonLibrary()
		{
			
			JSONObject data = new JSONObject();
			
			
			
			data.put("name","Vikash");
			data.put("location", "Pune");
			data.put("phone", "12345");
			
			String courses[] = {"c" , "c++"};
			
			data.put("courses", courses);
			
			int id =
			given()
			  .contentType("application/json")
			  .body(data.toString())               //only diff is to convert to string first and then pass
			.when()
			    .post("http://localhost:3000/students")
			.then()
			    .statusCode(201)
			    .header("Content-Type" , "application/json")
			    .body("name", equalTo("Vikash"))
			    .body("courses[0]", equalTo("c"))
			    .time(lessThan(2000L))
			    .log().all()
			.extract()
			    .jsonPath()
			    .getInt("id");
			    
			    
		}
		package Day2;

		import static io.restassured.RestAssured.given;
		import static org.hamcrest.Matchers.equalTo;
		import static org.hamcrest.Matchers.lessThan;

		import java.util.HashMap;

		import org.json.JSONObject;

		public class PostRequestBodyExamples {
			
			//1. create post request body using HashMap   ------------ small set of data
			//2. create post request body by org.json     ------------ small set of data 
			//3. Using POJO class (plain old java object) ------------ huge set of data 
			//4. create post request body using external json file --------- dont want to hard code the post data (request body)

			
			
			//1..
			public void createStudentUsingMap()
			{
				
				HashMap<String, Object> data = new HashMap<>();
				
				data.put("name","Vikash");
				data.put("location", "Pune");
				data.put("phone", "12345");
				
				String courses[] = {"c" , "c++"};
				
				data.put("courses", courses);
				
				int id =
				given()
				  .contentType("application/json")
				  .body(data)
				.when()
				    .post("http://localhost:3000/students")
				.then()
				    .statusCode(201)
				    .header("Content-Type" , "application/json")
				    .body("name", equalTo("Vikash"))
				    .body("courses[0]", equalTo("c"))
				    .time(lessThan(2000L))
				    .log().all()
				.extract()
				    .jsonPath()
				    .getInt("id");
				    
				    
			}
			
			
			//2.
			public void createStudentUsingJsonLibrary()
			{
				
				JSONObject data = new JSONObject();
				
				
				
				data.put("name","Vikash");
				data.put("location", "Pune");
				data.put("phone", "12345");
				
				String courses[] = {"c" , "c++"};
				
				data.put("courses", courses);
				
				int id =
				given()
				  .contentType("application/json")
				  .body(data.toString())               //only diff is to convert to string first and then pass
				.when()
				    .post("http://localhost:3000/students")
				.then()
				    .statusCode(201)
				    .header("Content-Type" , "application/json")
				    .body("name", equalTo("Vikash"))
				    .body("courses[0]", equalTo("c"))
				    .time(lessThan(2000L))
				    .log().all()
				.extract()
				    .jsonPath()
				    .getInt("id");
				    
				    
			}
			
			
			//3.
			public void createStudentUsingPojoClass()
			{
				
                StudentPojo requestBody = new StudentPojo();
                
                requestBody.setName("Vikash");
                requestBody.setLocation("Pune");
                requestBody.setPhone("12345");
				
				String courses[] = {"c" , "c++"};
				
				requestBody.setCourses(courses);
				
				int id =
				given()
				  .contentType("application/json")
				  .body(requestBody)
				.when()
				    .post("http://localhost:3000/students")
				.then()
				    .statusCode(201)
				    .header("Content-Type" , "application/json")
				    .body("name", equalTo(requestBody.getName()))
				    .body("courses[0]", equalTo(requestBody.getCourses()[0]))
				    .time(lessThan(2000L))
				    .log().all()
				.extract()
				    .jsonPath()
				    .getInt("id");
				    
				    
			}
			
		
}