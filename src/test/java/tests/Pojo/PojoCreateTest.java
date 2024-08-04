package tests.Pojo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class PojoCreateTest extends TestBase {

    @Test
    @DisplayName("Успешное Создание нового пользователя c именем и работой")
    void checkSuccessfulUserСreatTest() {
        String userData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    @DisplayName("Проверка невозможности создания нового пользователя без указания имени")
    void checkNotSuccessfulUserСreatWithoutNameTest() {
        String userData = "{\"name\", \"job\": \"singer\"}";

        given()
                .body(userData)
                .log().body()
                .log().uri()
                .log().headers()
                .contentType(JSON)

                .when()
                .post("/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }

    @Test
    @DisplayName("Проверка невозможности создания нового пользователя без указания работы")
    void checkNotSuccessfulUserСreatWithoutJobTest() {
        String userData = "{\"name\": \"morpheus\", \"job\"}";

        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }
}