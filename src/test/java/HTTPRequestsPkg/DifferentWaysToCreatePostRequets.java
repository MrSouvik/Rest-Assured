package HTTPRequestsPkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import PostRequestUsingPOJOClass.pojoPostRequest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
 * 1)Using HashMap
 * 2)Using org.Json
 * 3)Using POJO(Plain Old Java Object) class
 * 4)Using json file

 */

public class DifferentWaysToCreatePostRequets {
	//1)Using HashMap
	@Test
	void postRequestUsingHashMap() {
		HashMap data=new HashMap();
		data.put("name", "Souvik");
		data.put("job", "Test Engineer");
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo("Souvik"))
			.body("job",equalTo("Test Engineer"))
			.header("Content-Type","application/json; charset=utf-8")
			.log()
			.all();
		
	}
	//2)Using org.Json
	@Test
	void postRequestUsingOrgJson() {
		JSONObject data=new JSONObject();
		data.put("name", "Souvik");
		data.put("job", "SDET Engineer");
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo("Souvik"))
			.body("job",equalTo("SDET Engineer"))
			.header("Content-Type","application/json; charset=utf-8")
			.log()
			.all();
		
	}
	 //3)Using POJO(Plain Old Java Object) class
	@Test
	void postRequestUsingPOJOClass() {
		pojoPostRequest data=new pojoPostRequest();
		data.setName("Souvik");
		data.setJob("Automation Engineer");
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo("Souvik"))
			.body("job",equalTo("Automation Engineer"))
			.header("Content-Type","application/json; charset=utf-8")
			.log()
			.all();
		
	}
	//4)Using json file
	@Test
	void postRequestUsingjson() throws FileNotFoundException {
		File f=new File(".//src//test//resources/body.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject data=new JSONObject(jt);
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name", equalTo("Souvik"))
			.body("job",equalTo("Softwer Devalopment Engineer In Test"))
			.header("Content-Type","application/json; charset=utf-8")
			.log()
			.all();
		
	}
}
