package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;

public class FormatsTest {

    @BeforeAll
    public static void formatsTestSetup() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1/formats";
    }

    @Test
    public void getAllFormatsShouldReturnTwentyOneElements(){
        when().get().then().statusCode(200).body("formats", hasSize(21));
    }

    @Test
    public void getFormatsWithInvalidPathParamShouldReturnErrorNotFound(){
        given().pathParam("id", 1).get("/{id}").then().statusCode(404);
    }
}
