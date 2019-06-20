package petstore.endpoint;


import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import petstore.model.PetModel;
import java.io.File;

public class PetEndpoint {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());


    private RequestSpecification given(){
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json");
                //.log().uri();
    }


        @Step
        public ValidatableResponse getPetById(int petId) {
           // System.out.println("getPetById");
            log.info("Executing: getPetById");
            return given()
                    .get(Config.GET_PET_BY_ID, petId)
                    .then()
//                    .log().all()
                    .statusCode(200);
        }



    public enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    @Step
    public ValidatableResponse getPetByStatus(Status status) {

                return given()
                        .param("status",status)
                        .get(Config.GET_PET_BY_STATUS)
                        .then();
//                        .log().all();

    }

    @Step
    public ValidatableResponse createPet(PetModel petModel){
        return  given()
                .body(petModel)
                .post(Config.CREATE_PET)
                .then();
//                .log().all();
    }

    @Step
        public ValidatableResponse delPetById(int petId){

        return given()
                .delete(Config.DEL_PET_BY_ID,petId)
                .then();
//                .log().all();
    }

    @Step
    public ValidatableResponse updatePetById(PetModel petModel){

        return given()
                .body(petModel)
                .put(Config.UPDATE_PET)
                .then();
//                .log().all();
    }

    @Step
    public ValidatableResponse uploadPetImage(int petId, File file) {

        return given()
                .contentType("multipart/form-data")
                .multiPart(new MultiPartSpecBuilder(file)
                        .fileName(file.getName())
                        .build())
                .post(Config.UPLOAD_IMAGE_FOR_PET, petId)
                .then();

    }

}
