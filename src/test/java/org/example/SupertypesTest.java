package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;

public class SupertypesTest {

    @BeforeAll
    public static void supertypesTestSetup() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1/supertypes";
    }

    @Test
    public void getAllSupertypesShouldReturnSixElements(){
        when().get().then().statusCode(200).body("supertypes", hasSize(6));
    }

    @Test
    public void getSupertypesWithInvalidPathParamShouldReturnErrorNotFound(){
        given().pathParam("id", 1).get("/{id}").then().statusCode(404);
    }
}
