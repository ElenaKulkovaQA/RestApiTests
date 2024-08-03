package tests;

import model.LoginBodyModel;
import model.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSuccessfulTests extends TestBase {

    @Test
    @DisplayName("Проверка успешной авторизации с валидным логином и паролем")
    void successfulLoginTest() {
        //String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = given()

                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);// размаппить ответ и наложить его на класс LoginResponseModel

        assertEquals("QpwL5tke4Pnpja7X4", response.getToken());// проверить, что токен совпадает с ответом в классе

    }

    @Test
    @DisplayName("Проверка наличия текста _user not found_при неуспешной авторизации при введении невалидного пароля ")
    void userNotFoundInvalidPasswordTest() {
        String authData = "{\"email\": \"eveasdas.holt@reqres.in\", \"password\": \"cda\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));
    }

    @Test
    @DisplayName("Проверка наличия текста _user not found_при неуспешной авторизации при введении невалидного логина ")
    void userNotFoundInvalidLoginTest() {
        String authData = "{\"email\": \"eveasdas.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));
    }
}