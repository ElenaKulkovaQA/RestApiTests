package tests.Lombok;

import model.lombok.LoginBodyModelLombok;
import model.lombok.LoginResponseModelLombok;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LombokLoginSuccessfulTests extends TestBase {

    LoginBodyModelLombok authData = new LoginBodyModelLombok();

    @Test
    @DisplayName("Проверка успешной авторизации с валидным логином и паролем")
    void successfulLoginTest() {

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModelLombok response =

        step("Ввести валидный логин и пароль", () -> {
                    return
                            given()

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
                                    .extract().as(LoginResponseModelLombok.class);// размаппить ответ и наложить его на класс LoginResponseModel
                });

        step("Проверить соответствие токена в ответе", () -> {
            assertEquals("QpwL5tke4Pnpja7X4", response.getToken());// проверить, что токен совпадает с ответом в классе

        });
    }
}