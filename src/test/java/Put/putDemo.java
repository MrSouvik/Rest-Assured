package Put;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;


public class putDemo {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Get
		Response getResponse = given()
				.when()
				.get("/users/2")
				.then()
				.extract()
				.response();
		
		System.out.println("Before Put Response  - "+getResponse.body().asPrettyString());
		// Put
		File body = new File("./src/test/resources/putBody.json");
		Response putResponse = given()
				.header("content-Type","application/json")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(body)
				.when()
				.put("/users/2")
				.then()
				.statusCode(200)
				.extract().response();
		
		System.out.println("After Put Response  - "+putResponse.body().asPrettyString());		
	}
	
	
	
}
