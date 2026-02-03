package Day9;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RequestAndResponseSpecification {

    RequestSpecification httpRequest;
    ResponseSpecification httpResponse;

    @BeforeClass
    public void setup() {

        // 🔹 Request Specification
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setBaseUri("http://localhost:3000");
        reqBuilder.setBasePath("/employees");

        httpRequest = reqBuilder.build();

        // 🔹 Response Specification
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectStatusCode(200);
        resBuilder.expectHeader("Content-Type", containsString("application/json"));

        httpResponse = resBuilder.build();
    }

    @Test(priority = 1)
    public void getAllEmployees() {

        given()
            .spec(httpRequest)
        .when()
            .get()
        .then()
            .spec(httpResponse)
            .body("size()", greaterThan(0));
    }

    @Test(priority = 2)
    public void getMaleEmployees() {

        given()
            .spec(httpRequest)
            .queryParam("gender", "Male")
        .when()
            .get()
        .then()
            .spec(httpResponse)
            .body("gender", everyItem(equalTo("Male")));
    }
}
