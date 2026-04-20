package Day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class OAuth2AuthenticationDemo {

    @Test
    public void verifyOAuth2AuthenticationDemo() {

        String clientId = "your_client_id";
        String clientSecret = "your_client_secret";
        String redirectUri = "https://www.getpostman.com/oauth2/callback";
        String grantType = "authorization_code";
        String authorizationCode = "your_authorization_code";

        // Step 1: Get Access Token
        String token = given()
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .formParam("redirect_uri", redirectUri)
                .formParam("grant_type", grantType)
                .formParam("code", authorizationCode)
        .when()
                .post("https://api.imgur.com/oauth2/token")
        .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("access_token");

        System.out.println("Access Token: " + token);

        // Step 2: Use Token
        given()
                .auth().oauth2(token)
        .when()
                .get("https://api.imgur.com/3/account/me/images")
        .then()
                .statusCode(200)
                .log().all();
    }
}