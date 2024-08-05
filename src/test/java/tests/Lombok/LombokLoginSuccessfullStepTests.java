package tests.Lombok;

import model.lombok.LoginBodyModelLombok;
import model.lombok.LoginResponseModelLombok;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.RequestPackegeSpec.requestSpec;
import static specs.ResponsePackegeSpec.responseCode200Spec;

public class LombokLoginSuccessfullStepTests extends TestBase {

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
                                    .spec(requestSpec)
                                    .body(authData)

                                    .when()
                                    .post("login")

                                    .then()
                                    .spec(responseCode200Spec)
                                    .extract().as(LoginResponseModelLombok.class);// размаппить ответ и наложить его на класс LoginResponseModel
                });

        step("Проверить что строка с токеном не пустая, проверить соответствие токена в ответе, ", () -> {
            //assertEquals("QpwL5tke4Pnpja7X4", response.getToken());// проверить, что токен совпадает с ответом в классе
            assertThat(response.getToken()).isNotNull();
            assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");

        });
    }

}