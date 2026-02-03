package Day5;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponse {

	/*
	JSONObject getJSONResponse() {

        File myFile = new File(".\\src\\test\\resources\\complex.json");  // keep file in project root
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject jsonResponse = new JSONObject(jsonTokener);

        return jsonResponse;
    }
	
	*/
	
    // Method to read JSON file from resources
    JSONObject getJSONResponse() {

        JSONTokener tokener = new JSONTokener(
                getClass().getClassLoader().getResourceAsStream("complex.json")
        );

        return new JSONObject(tokener);
    }
    
    

    @Test
    void testUserDetailsValidation() {

        JsonPath jsonPath = JsonPath.from(getJSONResponse().toString());

        // a) Verify status
        String status = jsonPath.getString("status");
        assertThat(status, is("success"));

        // b) Validate user details
        int id = jsonPath.getInt("data.userDetails.id");
        String name = jsonPath.getString("data.userDetails.name");
        String email = jsonPath.getString("data.userDetails.email");

        assertThat(id, is(1234));
        assertThat(name, is("John Doe"));
        assertThat(email, is("john.deo@example.com"));

        // c) Validate home phone
        String homePhoneType = jsonPath.getString("data.userDetails.phoneNumbers[0].type");
        String homePhoneNumber = jsonPath.getString("data.userDetails.phoneNumbers[0].number");

        assertThat(homePhoneType, is("home"));
        assertThat(homePhoneNumber, is("123-456-7890"));

        // d) Validate geo coordinates
        double latitude = jsonPath.getDouble("data.userDetails.address.geo.latitude");
        double longitude = jsonPath.getDouble("data.userDetails.address.geo.longitude");

        assertThat(latitude, equalTo(39.7817));
        assertThat(longitude, equalTo(-89.6501));

        // e) Validate preferences
        boolean notification = jsonPath.getBoolean("data.userDetails.preferences.notification");
        String theme = jsonPath.getString("data.userDetails.preferences.theme");
        String language1 = jsonPath.getString("data.userDetails.preferences.language[0]");
        String language2 = jsonPath.getString("data.userDetails.preferences.language[1]");

        assertThat(notification, is(true));
        assertThat(theme, is("dark"));
        assertThat(language1, is("English"));
        assertThat(language2, is("Hindi"));
    }
}
