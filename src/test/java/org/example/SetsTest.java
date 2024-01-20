package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class SetsTest {

    @BeforeAll
    public static void setsTestSetup() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1/sets";
    }

    @Test
    public void getAllSetsShouldReturnFiveHundredElements(){
        when().get().then().statusCode(200).header("Count", equalTo("500"));
    }

    @Test
    public void getSetsWithInvalidPathShouldReturnErrorNotFound(){
        given().pathParam("id", 0).get("/{id}").then().statusCode(404);
    }

    @Test
    public void getSetsWithParametersShouldReturnCorrectData(){
        Map<String, String> expectedNames = Map.of(
                "khans", "Khans of Tarkir",
                "origins", "Magic Origins Clash Pack",
                "tenth", "Tenth Edition");
        expectedNames.keySet().forEach(name -> given().param("name", name).get().then().body("sets[0].name", equalTo(expectedNames.get(name))));
    }

    @Test
    public void getSetsWithSpecificPathShouldReturnCorrectData(){
        given().pathParam("id", "ktk").get("/{id}").then().body("set.name", equalTo("Khans of Tarkir"));
    }

    @Test
    public void getSetsWithBoosterPathParamShouldReturnCorrectData(){
        given().pathParam("id", "ktk").get("/{id}/booster").then().body("cards", hasSize(14));
    }
}
