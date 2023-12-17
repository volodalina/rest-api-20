package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ResourceTests {
    /*
    1. Make request to https://reqres.in/api/unknown
    2. Get response { "page": 2, "per_page": 6, "total": 12, ...
    3. Check total is 12
     */

    @Test
    void checkTotalListResource() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void checkTotalSingleResource() {
        given()
                .log().uri()
                .log().method()
                .log().body()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.name", is("fuchsia rose"));
    }


}
