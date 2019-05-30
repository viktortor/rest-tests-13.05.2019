package petstore.endpoint;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import petstore.model.PetModel;

public class PetEndpoint {

    private RequestSpecification given(){
        return RestAssured.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json")
                .log().uri();
    }



        public ValidatableResponse getPetById(int petId) {
            return given()
                    .get(Config.GET_PET_BY_ID, petId)
                    .then()
                    .log().all()
                    .statusCode(200);
        }



    public enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    public ValidatableResponse getPetByStatus(Status status) {

                return given()
                        .param("status",status)
                        .get(Config.GET_PET_BY_STATUS)
                        .then()
                        .log().all();

    }


    public ValidatableResponse createPet(PetModel petModel){

        return  given()
                .body(petModel)
                .post(Config.CREATE_PET)
                .then()
                .log().all();
    }


        public ValidatableResponse delPetById(int petId){

        return given()
                .delete(Config.DEL_PET_BY_ID,petId)
                .then()
                .log().all();
    }


    public ValidatableResponse updatePetById(PetModel petModel){

        return given()
                .body(petModel)
                .put(Config.UPDATE_PET)
                .then()
                .log().all();
    }


}
