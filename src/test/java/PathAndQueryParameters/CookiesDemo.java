package PathAndQueryParameters;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;



public class CookiesDemo {

	@Test
	void testCookies() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
		.cookie("AEC","hdgereiuwu_jjdhhfh37_jbdfdh")
		//this method will fail as cookie values are not same each time 
		.log().all();
	}
	
	@Test(priority=2)
	void testCookies2() {
		
		Response response=given()
		
		.when()
			.get("https://www.google.com/");
		//get single cookie info
		String cooikeValue=response.getCookie("AEC");
		System.out.println("The value of cookie is =>"+cooikeValue);
		//get all cookie information
		Map<String,String> cookies_variable=response.getCookies();
		//Print only keys Information
		System.out.println(cookies_variable.keySet());
		for(String k:cookies_variable.keySet()) {
			String cookie_value=response.getCookie(k);
			System.out.println(k+"     "+cookie_value);
		}
	}
}
