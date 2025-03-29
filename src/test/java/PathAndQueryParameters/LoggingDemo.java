package PathAndQueryParameters;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class LoggingDemo {

	@Test
	void loggingDemo() {
		given()
		
		.when()
			.get("https://www.google.com/")
		.then()
			.log().body()
			.log().cookies()
			.log().headers()
			.log().all();
	}
}
