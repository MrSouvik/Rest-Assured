package PathAndQueryParameters;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class QueryandPathParameters {
/*
 *   https://reqres.in/api/users?page=2&id=5
 *   URI=https://reqres.in
 *   API=/api
 *   PATH=/users
 *   QUARY=page=2&id=5
 */
	@Test
	void testQueryandPathParameters() {
		given()
			.pathParam("mypath", "users")
			.queryParam("page", 2)
			.queryParam("id", 5)
		.when()
		.get("https://reqres.in/api/{mypath}")
		//Path parameter we have to mention on the url itself using { }
		//Quary Parameter will go automatically along with request, no need to specify in the lik itself  
		.then()
		.statusCode(200)
		.log().all();
	}
}
