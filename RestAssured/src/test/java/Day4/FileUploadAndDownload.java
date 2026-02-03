package Day4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

import java.io.File;

import org.testng.annotations.Test;


public class FileUploadAndDownload {
	
	//1) single file upload

	@Test
	void  uploadSingleFile() {
		
		File myfile = new File("/Users/dhanojkumarsingh/Documents/apiFileUpload/Test1.txt");
		
		given()
		     .multiPart("file" , myfile)
		     .contentType("multipart/form-data")
		.when()
		    .post("https://the-internet.herokuapp.com/upload")
		.then()
		    .statusCode(200)
		  // .body("fileName" , equalTo("Test1.txt"))
		    .body(containsString("File Uploaded!"))
		    .body(containsString("Test1.txt"))
		    .log().body();
	}
	
	
	//@Test
	void  uploadMultipleFile() {
		
		File myfile1 = new File("C:\\Automation\\automatioonfiles\\test1.txt");
		File myfile2 = new File("C:\\Automation\\automatioonfiles\\test2.txt");
		
		given()
		     .multiPart("file" , myfile1)
		     .multiPart("file" , myfile2)
		     .contentType("multipart/form-data")
		.when()
		    .post("https://localhost:8080/uploadMultipleFiles")
		.then()
		    .statusCode(200)
		    .body("[0].fileName" , equalTo("Test1.txt"))
		    .body("[1].fileName" , equalTo("Test2.txt"))
		    .log().body();
	}

	
	//now download we can download single file at a time.
	
	//@Test
	void downloadFile() {
		
		given()
		    .pathParam("filename" , "Test1.txt")
		.when()
		    .get("http://localhost:8080/downloadFile/{filename}")
		.then()
		     .statusCode(200)
		     .log().body();
		
	}
	
}
