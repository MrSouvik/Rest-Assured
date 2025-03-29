package Post;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
public class PostRequestDemo {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://reqres.in/api";
		File body = new File("./src/test/resources/postRequest.json");
		Response response = given()
				.contentType("application/json")
				.body(body)
				.when()
				.post("/users")
				.then()
				.extract().response();
		
		System.out.println("Request Status code - "+response.getStatusCode());
		System.out.println("Request Response body - "+response.asPrettyString());
		
		//Json path
		JsonPath path = response.jsonPath();
		String name = path.getString("name");
		int age = path.getInt("age");
		String id = path.getString("id");
		
		System.out.println("Name : "+name);
		System.out.println("Age : "+age);
		System.out.println("Id : "+id);
		
		//Assertion 
		assertEquals(name, "Souvik Santra");
		File schemaFile = new File("./src/test/resources/schema.json");
		// schema validation 
		
		given()
				.contentType("application/json")
		.when()
				.get("users/1")
		 .then()
				.statusCode(200)
//				.log().all();
				.body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
		
		System.out.println("Json schema validation sucess!");
		
		
				
				
		
	}
}
