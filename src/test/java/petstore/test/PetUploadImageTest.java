package petstore.test;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoint.PetEndpoint;
import petstore.endpoint.UploadImageEndpoint;
import petstore.model.CategoryModel;
import petstore.model.PetModel;
import petstore.model.TagModel;
import java.io.File;
import static org.hamcrest.CoreMatchers.is;

@Concurrent
@RunWith(SerenityRunner.class)
public class PetUploadImageTest {
    @Steps
    private UploadImageEndpoint uploadImageEndpoint;
    private PetModel petModel;
    @Steps
    private PetEndpoint petEndpoint;



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
    public void uploadPetPhotoTest(){

        String pathName = "D:\\Loboda\\courses\\testData\\";
        String fileName = "cloud.jpg";

        File file = new File(pathName + fileName);

        uploadImageEndpoint
                .uploadPetImage(323, file)
                .body("code", is(200))
                .body("message", is("additionalMetadata: null" + "\n" +
                        "File uploaded to ./" + fileName + ", " + file.length() + " bytes"));
    }



}



