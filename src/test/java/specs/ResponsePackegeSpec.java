package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;

public class ResponsePackegeSpec {

    public static ResponseSpecification responseCode200Spec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification responseCode400Spec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();

    public static ResponseSpecification responseCode201Spec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();
}
