import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class WeatherTest {

    @Test
    public void getWeatherPerCityTest(){
        RestAssured.baseURI = "https://pinformer.sinoptik.ua/search.php";
        ValidatableResponse searchCityResponse = RestAssured.given()
                .param("lang","ua")
                .param("return_id",1)
                .param("q","Lviv")
                //.log().uri()
                .get()
                .then()
                //.log().all()
                .statusCode(200);
    String cityId = (searchCityResponse.extract().asString()).substring((searchCityResponse.extract().asString()).lastIndexOf("|")+1);
        System.out.println(cityId);


        RestAssured.baseURI = "https://pinformer.sinoptik.ua/pinformer4.php";
        ValidatableResponse weatherByIdResponse = RestAssured.given()
                .param("lang","ua")
                .param("id",cityId)
                .log().uri()
                .get()
                .then()
                //.log().all()
                .statusCode(200);
//https://pinformer.sinoptik.ua/pinformer4.php?type=js&lang=ru&id=303014487
    }
}
