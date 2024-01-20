package org.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class CardsTest {

    @BeforeAll
    public static void testSetup() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1/cards";
    }

    @Test
    public void getAllCardsShouldReturnOneHundredElements(){
        when().get().then().statusCode(200).header("Count", equalTo("100"));
    }

    @Test
    public void getCardsWithInvalidIDShouldReturnErrorNotFound(){
        given().pathParam("id", 0).get("/{id}").then().statusCode(404);
    }

    @Test
    public void getCardsWithSpecificIDShouldReturnCorrectData(){
        Map<Integer, String> expectedNames = Map.of(
                1, "Ankh of Mishra",
                2, "Basalt Monolith",
                3, "Black Lotus");
        expectedNames.keySet().forEach(id -> given().pathParam("id", id).get("/{id}").then().body("card.name", equalTo(expectedNames.get(id))));
    }

}
