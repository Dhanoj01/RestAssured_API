package Day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class PostRequestBodyExamples {

    // 1. Using HashMap
    @Test
    public void createStudentUsingMap() {

        HashMap<String, Object> data = new HashMap<>();

        data.put("name", "Vikash");
        data.put("location", "Pune");
        data.put("phone", "12345");

        String courses[] = {"c", "c++"};
        data.put("courses", courses);

        given()
            .contentType("application/json")
            .body(data)
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .header("Content-Type", "application/json")
            .body("name", equalTo("Vikash"))
            .body("courses[0]", equalTo("c"))
            .time(lessThan(2000L))
            .log().all();
    }

    // 2. Using JSONObject
    @Test
    public void createStudentUsingJsonLibrary() {

        JSONObject data = new JSONObject();

        data.put("name", "Vikash");
        data.put("location", "Pune");
        data.put("phone", "12345");

        String courses[] = {"c", "c++"};
        data.put("courses", courses);

        given()
            .contentType("application/json")
            .body(data.toString())
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .header("Content-Type", "application/json")
            .body("name", equalTo("Vikash"))
            .body("courses[0]", equalTo("c"))
            .time(lessThan(2000L))
            .log().all();
    }

    // 3. Using POJO Class
    @Test
    public void createStudentUsingPojoClass() {

        StudentPojo requestBody = new StudentPojo();

        requestBody.setName("Vikash");
        requestBody.setLocation("Pune");
        requestBody.setPhone("12345");

        String courses[] = {"c", "c++"};
        requestBody.setCourses(courses);

        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .header("Content-Type", "application/json")
            .body("name", equalTo(requestBody.getName()))
            .body("courses[0]", equalTo(requestBody.getCourses()[0]))
            .time(lessThan(2000L))
            .log().all();
    }
}