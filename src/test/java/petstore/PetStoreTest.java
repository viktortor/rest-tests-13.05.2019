package petstore;

import io.restassured.RestAssured;
import org.junit.Test;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

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

        for (Status status : Status.values()) {

             RestAssured.given()
                    .param("status", status)
                    .log().uri()
                    .get(Config.GET_PET_BY_STATUS)

                    .then()
                    .log().all()
                    .statusCode(200);
}

    }


    @Test
    public void createPetTest(){
        PetModel petModel = new PetModel(
                323,
                 new CategoryModel(),
                 "NewMyPet",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");


        RestAssured.given()
               // .header("Content-Type", "application/json")
                .contentType("application/json")
                .body(petModel)
                .log().uri()
                .post(Config.CREATE_PET)

                .then()
                .log().all()
                .statusCode(200);
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
