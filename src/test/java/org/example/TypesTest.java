package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;

public class TypesTest {

    @BeforeAll
    public static void testSetup() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1/types";
    }

    @Test
    public void getAllTypesShouldReturnTwentyFiveElements(){
        when().get().then().statusCode(200).body("types", hasSize(25));
    }

    @Test
    public void getTypesWithInvalidPathParamShouldReturnErrorNotFound(){
        given().pathParam("id", 1).get("/{id}").then().statusCode(404);
    }
}
