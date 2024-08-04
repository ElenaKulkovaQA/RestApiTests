package tests.Pojo;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PojoGetResourseTests extends TestBase {

    @Test
    @DisplayName("Получить размер списка всех ресурсов")
    void getSizeOfAllResoursesListTest() {
        int allResourse = 12;
        given()
                .log().uri()
                .when()
                .get("unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(allResourse));
    }

    @Test
    @DisplayName("Получить размер списка ресурсов на странице")
    void getUsersListSizeOnThePageesResoursesTest() {
        int usersListSize = 6;
        given()
                .log().uri()
                .when()
                .get("unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data", hasSize(usersListSize));
    }

    @Test
    @DisplayName("Сравнить ожидаемый список Id с полученным после запроса")
    void compareExpectedListOfIdsAndActualTest() {
        List<Integer> expectedId = List.of(1, 2, 3, 4, 5, 6);
        Response response = given()
                .log().uri()
                .when()
                .get("unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();
        List<Integer> actualId = response.jsonPath().getList("data.id");
        assertEquals(expectedId, actualId);
    }
}
