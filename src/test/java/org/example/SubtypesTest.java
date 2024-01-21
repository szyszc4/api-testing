package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;

public class SubtypesTest {

    @BeforeAll
    public static void subtypesTestSetup() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1/subtypes";
    }

    @Test
    public void getAllSubtypesShouldReturnFourHundredNinetyOneElements(){
        when().get().then().statusCode(200).body("subtypes", hasSize(491));
    }

    @Test
    public void getSubtypesWithInvalidPathParamShouldReturnErrorNotFound(){
        given().pathParam("id", 1).get("/{id}").then().statusCode(404);
    }
}
