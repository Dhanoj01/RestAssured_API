package Dayy11;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



/*
 * GraphQL Testing with REST Assured
 * JSON Converter : https://datafetcher.com/graphql-json-body-converter
 * GraphQL queries must be wrapped in JSON format
 */


public class GraphQL_QueryTests {

	
	private static final String BASE_URL = "https://hasura.io/learn/graphql";
	private static final String AUTH_TOKEN = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY5ODE5ZGI1MmI5NjY0ZWRjOWJhODJhMSJ9LCJuaWNrbmFtZSI6ImRoYW5vamt1bWFyc2luZ2g5IiwibmFtZSI6ImRoYW5vamt1bWFyc2luZ2g5QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci82YTg5YjE5ZjdjMjliM2JmM2Y2Zjc1NTIxMDVjOWJiMT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmRoLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDI2LTAyLTAzVDA3OjAzOjE5LjIyOVoiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsInN1YiI6ImF1dGgwfDY5ODE5ZGI1MmI5NjY0ZWRjOWJhODJhMSIsImlhdCI6MTc3MDEwMjIwMCwiZXhwIjoxNzcwMTM4MjAwLCJzaWQiOiJkM2NqTzhkN3NyM25QbkUxNFo3akxBa04wcEZXYm1wWSIsImF0X2hhc2giOiJTUGo0bEJiVmh2a0NMRndjSEljTXhBIiwibm9uY2UiOiJoYlBKeDBPTWlSZWVtcUdtWTJpOGl6NTRIUHg4dndsUSJ9.pxLm0G3g7LR0m0LKvL66kMb3ehVMBgD5tU45C8eOE3yhZdzkhZJkmz-GhljO6KxxEB4pe1e-SGihy6ndBeVB6PVTPzvImRQPYLObEJn9cmQ09O00g6w6rSnXtpCq5shjrECUAUZaTM5i553lu0yiLDLMtumJuJsR63IpN2zmVd26kPUeCfS0N-1stXqZ3afX_kvHaaa6eDmJ9NyUHRZ8a2SLViNmU8rP8vlPm82xGoxrshZZL5qixQZT2UJIpjsprszjHnlzcMan9MPBpnLt9KiXSAc1lNANgC-cb-EjVI48zfXSMCwRD5mPLO9_1v2JNii-c46gQm5nnGsitFR28g";
	
	//Fetch Users and their Todos
//	@Test(priority=1)
	public void testFetchUsersAndTools()
	{
		String graphqlQuery = "{\n"
				+ "  \"query\": \"query{ users { name todos { title } }}\"\n"
				+ "}";
		
		given()
		    .contentType("application/json")     //.contentType(ContentType.JSON)
		    .header("Authorization" , AUTH_TOKEN)
		    .body(graphqlQuery)
	    .when()
	       .post(BASE_URL)
	    .then()
	        .statusCode(200)
	        .log().body();
	}
	
	
	//2. Fetch Users with limit Todos
	//	@Test(priority=2)
		public void testFetchLimitedTools()
		{
			String graphqlQuery = "";
			
			given()
			    .contentType("application/json")     //.contentType(ContentType.JSON)
			    .header("Authorization" , AUTH_TOKEN)
			    .body(graphqlQuery)
		    .when()
		       .post(BASE_URL)
		    .then()
		        .statusCode(200)
		        .body("data.todos",hasSize(lessThanOrEqualTo(5)))
		        .body("data.todos[0].id", notNullValue())
		        .body("data.todos[0].title", notNullValue())
		        .log().body();
		}
		
		//3. Fetch Users and their recent Todos
			//	@Test(priority=3)
				public void testFetchUserWithRecentTools()
				{
					String graphqlQuery = "{\n"
							+ "  \"query\": \"query{ users (limit:2){ id name todos (order_by : {created_at: desc}, limit: 5){ id title } }}\"\n"
							+ "}";
					
					given()
					    .contentType("application/json")     //.contentType(ContentType.JSON)
					    .header("Authorization" , AUTH_TOKEN)
					    .body(graphqlQuery)
				    .when()
				       .post(BASE_URL)
				    .then()
				        .statusCode(200)
				        .body("data.users",hasSize(2))
				        .body("data.users[0].todos", hasSize(lessThanOrEqualTo(1)))
				        .body("data.users[0].name", notNullValue())
				        .log().body();
				}
				
				
				//4. Fetch todos using variable
			//	@Test(priority=4)
				public void testFetchTodosWithVariable()
				{
					String graphqlQuery = "{\n"
							+ "  \"query\": \"query($limit: Int!){todos(limit: $limit){idtitle}}\",\n"
							+ "  \"variables\": {\n"
							+ "    \"limit\": 10\n"
							+ "  }\n"
							+ "}";
					
					given()
					    .contentType("application/json")     //.contentType(ContentType.JSON)
					    .header("Authorization" , AUTH_TOKEN)
					    .body(graphqlQuery)
				    .when()
				       .post(BASE_URL)
				    .then()
				        .statusCode(200)
				        .body("data.todos",hasSize(10))
				        .body("data.todos[0].id", notNullValue())
				        .body("data.todos[0].title", notNullValue())
				        .log().body();
				}
				
				//5. Fetch public todos
				@Test(priority=5)
				public void testFetchPublicTodos()
				{
					String graphqlQuery = "{\n"
							+ "  \"query\": \"query{ todos(where: {is_public: {_eq: true}}){ title is_public is_completed }}\"\n"
							+ "}";
					
					given()
					    .contentType("application/json")     //.contentType(ContentType.JSON)
					    .header("Authorization" , AUTH_TOKEN)
					    .body(graphqlQuery)
				    .when()
				       .post(BASE_URL)
				    .then()
				        .statusCode(200)
				        .body("data.todos",hasSize(greaterThan(0)))
				        .body("data.todos[0].is_public", equalTo(true))
				        .body("data.todos[0].is_completed", notNullValue())
				        .log().body();
					
				}
				   
}
