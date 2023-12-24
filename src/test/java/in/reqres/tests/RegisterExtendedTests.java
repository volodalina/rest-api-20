package in.reqres.tests;

import in.reqres.models.RegisterBodyPojoModel;
import in.reqres.models.RegisterBodyLombokModel;
import in.reqres.models.RegisterResponsePojoModel;
import in.reqres.models.RegisterResponseLombokModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterExtendedTests {

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
                .extract().as(RegisterResponsePojoModel.class);
        
        assertEquals("4", registerResponse.getId());
        assertEquals("QpwL5tke4Pnpja7X4", registerResponse.getToken());
    }

    
    @Test
    void successfulRegisterWithLombokModelsTest() {
 
        RegisterBodyLombokModel authData = new RegisterBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        RegisterResponseLombokModel registerResponse = given()
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
                // .body("id", is(4))
                .extract().as(RegisterResponseLombokModel.class);

        assertEquals("4", registerResponse.getId());
        assertEquals("QpwL5tke4Pnpja7X4", registerResponse.getToken());
    }

    // @Override
    // public String toString() {
    //     return "RegisterExtendedTest []";
    // }
}
