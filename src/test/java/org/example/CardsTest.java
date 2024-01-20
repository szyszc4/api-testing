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
    public static void cardsTestSetup() {
        RestAssured.baseURI = "https://api.magicthegathering.io/v1/cards";
    }

    @Test
    public void getAllCardsShouldReturnOneHundredElements(){
        when().get().then().statusCode(200).header("Count", equalTo("100"));
    }

    @Test
    public void getCardsWithInvalidPathShouldReturnErrorNotFound(){
        given().pathParam("id", 0).get("/{id}").then().statusCode(404);
    }

    @Test
    public void getCardsWithParametersShouldReturnCorrectData(){
        given().
                param("id", "33b9ca30-0296-52b7-a8e2-7d4715404b0d|f592d20b-1974-569e-82ab-aa59f3a66765").
                param("layout", "normal").
                param("legality", "Legal").
        get().then().
                header("Count", equalTo("2"));
    }

    @Test
    public void getCardsWithSpecificPathShouldReturnCorrectData(){
        Map<Integer, String> expectedNames = Map.of(
                1, "Ankh of Mishra",
                2, "Basalt Monolith",
                3, "Black Lotus");
        expectedNames.keySet().forEach(id -> given().pathParam("id", id).get("/{id}").then().body("card.name", equalTo(expectedNames.get(id))));
    }

}
