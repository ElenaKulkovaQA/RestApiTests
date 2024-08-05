package tests.Lombok;

import model.lombok.LoginBodyModelLombok;
import model.lombok.LoginResponseModelLombok;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RequestPackegeSpec.requestSpec;
import static specs.ResponsePackegeSpec.*;

public class LombokLoginUnSuccеssfullStepTests extends TestBase {

    LoginBodyModelLombok authData = new LoginBodyModelLombok();

    @Test
    @DisplayName("Проверка наличия текста _Missing password_при неуспешной авторизации при введении невалидного пароля ")
    void userNotFoundInvalidPasswordTest() {

        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("");

        LoginResponseModelLombok response =

                step("Ввести валидный логин и невалидный пароль", () -> {
                    return
                            given()
                                    .spec(requestSpec)
                                    .body(authData)

                                    .when()
                                    .post("/login")

                                    .then()
                                    .spec(responseCode400Spec)
                                    .extract().as(LoginResponseModelLombok.class);
                });

        step("Проверить соответствие текста в ответе", () -> {
            assertEquals("Missing password", response.getError());

        });
    }


    @Test
    @DisplayName("Проверка наличия текста _user not found_при неуспешной авторизации при введении невалидного логина ")
    void userNotFoundInvalidLoginTest() {

        authData.setEmail("eveasdas.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModelLombok response =

                step("Ввести валидный пароль и невалидный логин", () -> {
                    return
                            given()
                                    .spec(requestSpec)
                                    .body(authData)

                                    .when()
                                    .post("/login")

                                    .then()
                                    .spec(responseCode400Spec)
                                    .extract().as(LoginResponseModelLombok.class);
                });

        step("Проверить соответствие текста в ответе", () -> {
            assertEquals("user not found", response.getError());

        });
    }
}