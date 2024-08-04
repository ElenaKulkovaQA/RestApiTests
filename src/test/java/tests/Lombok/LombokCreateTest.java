package tests.Lombok;

import model.lombok.CreateBodyModelLombok;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import utils.TestData;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class LombokCreateTest extends TestBase {

    CreateBodyModelLombok authData = new CreateBodyModelLombok();
    private TestData testData = new TestData();

    @Test
    @DisplayName("Успешное Создание нового пользователя c именем и работой")
    void checkSuccessfulUserСreatTest() {
        authData.setJob(testData.job);
        authData.setName(testData.name);

        given()

                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .contentType(JSON)

                .when()
                .post("/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", equalTo(authData.getName()))
                .body("job", equalTo(authData.getJob()));
    }


    @Test
    @DisplayName("Проверка невозможности создания нового пользователя без указания имени")
    void checkNotSuccessfulUserСreatWithoutNameTest() {
        String userData = "{\"name\", \"job\": \"singer\"}";
//        authData
//                .setJob(testData.job);

        given()

                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(userData)
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

//        authData
//                .setName(testData.name);

        given()

                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(userData)
                .contentType(JSON)

                .when()
                .post("/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }
}