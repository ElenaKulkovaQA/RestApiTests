package tests.Lombok;

import io.qameta.allure.restassured.AllureRestAssured;
import model.lombok.LoginBodyModelLombok;
import model.lombok.LoginResponseModelLombok;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LombokLoginUnSuccеssfullTests extends TestBase {

    LoginBodyModelLombok authData = new LoginBodyModelLombok();

    @Test
    @DisplayName("Проверка наличия текста _Missing password_при неуспешной авторизации при введении невалидного пароля ")
    void userNotFoundInvalidPasswordTest() {

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("");

        LoginResponseModelLombok response = given()

                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .contentType(JSON)

                .when()
                .post("/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(LoginResponseModelLombok.class);
        assertEquals("Missing password", response.getError());
    }


    @Test
    @DisplayName("Проверка наличия текста _user not found_при неуспешной авторизации при введении невалидного логина ")
    void userNotFoundInvalidLoginTest() {

        authData.setEmail("eveasdas.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModelLombok response = given()

                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .contentType(JSON)

                .when()
                .post("/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(LoginResponseModelLombok.class);
        assertEquals("user not found",response.getError());
    }
}