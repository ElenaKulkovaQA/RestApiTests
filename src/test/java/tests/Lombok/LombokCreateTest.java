package tests.Lombok;

import model.lombok.CreateBodyModelLombok;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import utils.TestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static specs.CreateSpec.*;
import static specs.LoginSpec.*;

public class LombokCreateTest extends TestBase {

    CreateBodyModelLombok authData = new CreateBodyModelLombok();
    private TestData testData = new TestData();

    @Test
    @DisplayName("Успешное Создание нового пользователя c именем и работой")
    void checkSuccessfulUserСreatTest() {
        authData.setJob(testData.job);
        authData.setName(testData.name);

        given()

                .spec(createRequestSpec)
                .body(authData)

                .when()
                .post("/users")

                .then()
                .spec(successfulUserСreatResponseSpec)

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

                .spec(createRequestSpec)
                .body(userData)

                .when()
                .post("/users")

                .then()
                .spec(userNotCreatResponseSpec);

    }

    @Test
    @DisplayName("Проверка невозможности создания нового пользователя без указания работы")
    void checkNotSuccessfulUserСreatWithoutJobTest() {
        String userData = "{\"name\": \"morpheus\", \"job\"}";

//        authData
//                .setName(testData.name);

        given()

                .spec(createRequestSpec)
                .body(userData)

                .when()
                .post("/users")

                .then()
                .spec(userNotCreatResponseSpec);

    }
}