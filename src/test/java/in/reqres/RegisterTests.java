package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegisterTests {
    /*
    1. Make request (POST) to https://reqres.in/api/register
        with body { "email": "eve.holt@reqres.in", "password": "pistol" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check token is QpwL5tke4Pnpja7X4
     */

    @Test
    void successfulRegisterTest() {
        String authData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }"; // BAD PRACTICE

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void missingPasswordTest() {
        String authData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"\" }"; // BAD PRACTICE

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void missingEmailTest() {
        String authData = "{ \"email\": \"\", \"password\": \"pistol\" }"; // BAD PRACTICE

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void negative400Test() {
        String authData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }"; // BAD PRACTICE

        given()
                .log().uri()
                .log().method()
                .log().body()
                .body(authData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

}
