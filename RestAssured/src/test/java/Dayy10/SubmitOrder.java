package Dayy10;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class SubmitOrder {

    private static final String AUTH_TOKEN = "Bearer 1081b7b207bbfedd87ccca181f26a386f96224ba836f759a19f847e715506cf6";
    private static final String BASE_URL = "https://simple-books-api.glitch.me/orders/";

    @Test
    public void testSubmitAndDeleteOrder() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("bookId", 1);
        requestBody.put("customerName", "John");

        // ✅ FIXED CHAIN
        String orderId = given()
                .contentType("application/json")
                .header("Authorization", AUTH_TOKEN)
                .body(requestBody.toString())
        .when()
                .post(BASE_URL)
        .then()
                .statusCode(201)
                .log().body()
        .extract()
                .jsonPath().getString("orderId");

        System.out.println("Created Order ID: " + orderId);

        // DELETE ORDER
        given()
                .header("Authorization", AUTH_TOKEN)
                .pathParam("orderId", orderId)
        .when()
                .delete(BASE_URL + "/{orderId}")
        .then()
                .statusCode(204);

        System.out.println("Order deleted successfully");
    }
}
