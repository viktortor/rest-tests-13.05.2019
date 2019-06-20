package petstore.test;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoint.PetEndpoint;
import petstore.model.CategoryModel;
import petstore.model.PetModel;
import petstore.model.TagModel;
import java.io.File;
import static org.hamcrest.Matchers.containsString;

@Concurrent
@RunWith(SerenityRunner.class)
public class PetUpdateTest {

    @Steps
    private PetEndpoint petEndpoint;
    private PetModel petModel;

    @Before
    public void preCondition(){
        petModel = new PetModel(
                323,
                new CategoryModel(),
                "NewMyPet",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndpoint
                .createPet(petModel)
                .statusCode(200);
    }


    @After
    public void postCondition(){
               petEndpoint
                .delPetById(petModel.getId())
                .statusCode(200);

    }


    @Test
    public void updatePetTest(){
        petModel.setName("tiger");
        petModel.setStatus("SOLD");
        petEndpoint
                .updatePetById(petModel)
                .statusCode(200);

        petEndpoint
                .getPetById(petModel.getId())
                .statusCode(200);
    }



    @Test
    public void uploadPetPhotoTest(){
        File petImageFile = new File(getClass().getClassLoader().getResource("cloud.jpg").getFile());
        petEndpoint
                .uploadPetImage(2, petImageFile)
                .log().all()
                .statusCode(200)
                .body("message", containsString(petImageFile.getName()));

    }






}
