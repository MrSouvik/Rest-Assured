package Delete;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class DeleteDemo {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://reqres.in/api";
		
		Response response = given()
				.header("content-Type","application/json")
				.when()
				.delete("/users/2")
				.then()
				.extract().response();
		
		System.out.println("Response body - "+response.body().asPrettyString());
		System.out.println("Response status code "+response.getStatusCode());
		System.out.println("Response time "+response.time());
				
	}
}
