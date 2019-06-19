package petstore.endpoint;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import java.io.File;

public class UploadImageEndpoint {
   // Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private RequestSpecification given() {
        SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
        return SerenityRest.given()
                .baseUri(Config.BASE_URI)
                .accept("application/json")
                .contentType("multipart/form-data");
    }

    @Step
    public ValidatableResponse uploadPetImage(int petId, File file) {

        return given()
                .multiPart(file)
                .multiPart("additionalMetadata", "null")
                .post(Config.UPLOAD_IMAGE_FOR_PET, petId)
                .then()
                .statusCode(200);
    }



}
