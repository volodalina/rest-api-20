package in.reqres.tests;

import in.reqres.models.RegisterBodyPojoModel;
import in.reqres.models.RegisterResponsePojoModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RegisterExtendedTest {
        /*
    1. Make request (POST) to https://reqres.in/api/register
        with body { "email": "eve.holt@reqres.in", "password": "pistol" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check token is QpwL5tke4Pnpja7X4
     */

    @Test
    void successfulRegisterBadPracticeTest() {
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
    void successfulRegisterWithPojoModelsTest() {
 
        RegisterBodyPojoModel authData = new RegisterBodyPojoModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        RegisterResponsePojoModel registerResponse = given()
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
                .extract().as(RegisterResponsePojoModel.class);
                
        assertEquals("QpwL5tke4Pnpja7X4", registerResponse.getToken());
    }

    @Override
    public String toString() {
        return "RegisterExtendedTest []";
    }
}
