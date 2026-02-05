package Dayy10;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class DataDrivenTesting {
	
    private static final String AUTH_TOKEN = "Bearer 1081b7b207bbfedd87ccca181f26a386f96224ba836f759a19f847e715506cf6";
    private static final String BASE_URL = "https://simple-books-api.glitch.me/orders/";

    //for Excel
    @Test(dataProvider = "excelDataProvider", dataProviderClass = DataProviders.class)
    public void testWithExcelData(String bookId, String customerName) {
        testSubmitAndDeleteOrder(bookId, customerName);
    }
    
    //for JSON
    @Test(dataProvider = "jsonDataProvider", dataProviderClass = DataProviders.class)
    public void testWithJsonData(Map<String,String>data) {
        testSubmitAndDeleteOrder(data.get("BookId") , data.get("CustomerName"));
    }
    
    //for csv
    @Test(dataProvider = "csvDataProvider", dataProviderClass = DataProviders.class)
    public void testWithCsvData(Map<String,String>data) {
        testSubmitAndDeleteOrder(data.get("BookId") , data.get("CustomerName"));
    }
    
    
    
    
    
    
    public void testSubmitAndDeleteOrder(String bookId, String customerName) {

        JSONObject requestBody = new JSONObject();
        requestBody.put("bookId", Integer.parseInt(bookId));  // ✅ FIX 1
        requestBody.put("customerName", customerName);

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

        given()
                .header("Authorization", AUTH_TOKEN)
                .pathParam("orderId", orderId)
        .when()
                .delete(BASE_URL + "{orderId}")   // ✅ FIX 2 (removed extra /)
        .then()
                .statusCode(204);

        System.out.println("Order deleted successfully");
    }
}
