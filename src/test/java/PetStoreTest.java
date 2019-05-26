import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PetStoreTest {

    static {
        RestAssured.baseURI = Config.BASE_URI;
    }

    private enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    @Test
    public void getPetByIdTest(){
        int petId = 1;

        RestAssured.given()
                 //.log().uri()
                .get(Config.GET_PET_BY_ID,petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getPetByStatusTest() {

        List statuses = Arrays.asList(Status.values());
        statuses.forEach (status ->

            RestAssured.given()
                    .param("status", status)
                    .log().uri()
                    .get(Config.GET_PET_BY_STATUS)

                    .then()
                    .log().all()
                    .statusCode(200)
        );

    }

        @Test
        public void delPetByIdTest(){
            int petId = 1;

            RestAssured.given()
                    //.log().uri()
                    .delete(Config.DEL_PET_BY_ID,petId)
                    .then()
                    .log().all()
                    .statusCode(200);

    }



}
