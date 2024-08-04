package tests.Pojo;

import model.pojo.LoginBodyModelPojo;
import model.pojo.LoginResponseModelPojo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PojoLoginSuccessfulTests extends TestBase {

    LoginBodyModelPojo authData = new LoginBodyModelPojo();

    @Test
    @DisplayName("Проверка успешной авторизации с валидным логином и паролем")
    void successfulLoginTest() {
        //String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModelPojo response = given()

                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .contentType(JSON)

                .when()
                .post("login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModelPojo.class);// размаппить ответ и наложить его на класс LoginResponseModel

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