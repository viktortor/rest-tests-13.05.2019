import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class WeatherTest {

    @Test
    public void getWeatherPerCityTest(){
        String cityName = "Lviv";
        RestAssured.baseURI = "https://pinformer.sinoptik.ua/";
        ValidatableResponse response = RestAssured.given()
                .basePath ("search.php")
                .param("lang","ua")
                .param("return_id",1)
                .param("q",cityName)
                //.log().uri()
                .get()
                .then()
                //.log().all()
                .statusCode(200);
    String responseString = response.extract().asString();
        String cityId = responseString.substring(responseString.lastIndexOf("|")+1);
        System.out.println(cityId);

        ValidatableResponse weatherByIdResponse = RestAssured.given()
                .param("type","js")
                .param("lang","ua")
                .param("id",cityId)
                .log().uri()
                .get("pinformer4.php")
                .then()
                .log().all()
                .statusCode(200)
                .body("any{it.key == '{pcity}'}", is(true))//groovy path with hamcrest matchers
                .body("'{pcity}'", is(cityId));// JSON path with hamcrest matchers
        //System.out.println(weatherByIdResponse.extract().path("'{pcity}'"));

    }
}
