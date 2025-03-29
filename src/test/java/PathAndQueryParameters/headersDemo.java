package PathAndQueryParameters;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class headersDemo {

	@Test
	void testHeader() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws");
	} 
	
	@Test(priority=2)
	void testHeaders() {
		
		Response response=given()
		
		.when()
			.get("https://www.google.com/");
		String contentType=response.getHeader("Content_Type");
		System.out.println("Content Type Header Value :"+contentType);
		//get all header info
		Headers headers=response.getHeaders();
		
		for(Header header:headers) {
			String header_name=header.getName();
			String header_value=header.getValue();
			System.out.println(header_name+"    "+header_value);
			System.out.println("---------------------------------------------------------------------------------------");
		}
	}
}
