package Get;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class GetRequestDemo {
	public static void main(String[] args) {
		//Basic GET Request Using Rest Assured
		getmethod1();
		//Storing the Response and Validating Fields
		getmethod2();
		//GET Request with Query Parameters
		getmethod3();
		//GET Request with Headers
		getmethod4();
		//Validating Response Time
		getmethod5();
		//Handling SSL Certificates
		getmethod6();
		
	}

	private static void getmethod6() {
		 Response response = given()
	        .relaxedHTTPSValidation()
	    .when()
	        .get("https://self-signed.badssl.com/")
	    .then()
	        .statusCode(200).extract().response();
		 System.out.println("Response "+response.asPrettyString() );
		
	}

	private static void getmethod5() {
		 Response response = given()
		    .when()
		        .get("https://jsonplaceholder.typicode.com/posts/1")
		    .then()
		        .time(lessThan(2000L)).extract().response();
		 System.out.println("Response time : "+response.time());
		
	}

	private static void getmethod4() {
		given()
			.header("Accept","application/json")
			.header("Authorization","Bearer your_access_token")
		.when()
			.get("https://jsonplaceholder.typicode.com/posts/1")
		.then()
			.statusCode(200)
			.log().headers();
		
	}

	private static void getmethod3() {
		given()
				.queryParam("userId", 1)
				.when()
				.get("https://jsonplaceholder.typicode.com/posts")
				.then()
				.statusCode(200)
				.body("[0].userId",equalTo(1))
				.log().body();
		
	}

	private static void getmethod2() {
		Response response = given().when()
				.get("https://jsonplaceholder.typicode.com/posts/1")
				.then()
				.statusCode(200)
				.extract().response();
		System.out.println("response Body - "+response.asString());
		Assert.assertEquals(response.jsonPath().getInt("id"), 1);
		Assert.assertNotNull(response.jsonPath().getString("userId"));
		
		
	}

	private static void getmethod1() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		given().
			when().get("/posts/1")
			.then()
			.statusCode(200)
			.body("id",equalTo(1))
			.body("userId",notNullValue())
			.log().all();
	}
}
