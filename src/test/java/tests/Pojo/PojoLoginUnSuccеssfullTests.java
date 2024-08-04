package tests.Pojo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class PojoLoginUnSuccеssfullTests extends TestBase {

    @Test
    @DisplayName("Проверка сообщения при неуспешной попытке залогиниться без введения пароля")
    void checkSuccessfulUserСreatTest() {

        String authData = "{\"email\": \"eeter@klaven\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));

    }
}
