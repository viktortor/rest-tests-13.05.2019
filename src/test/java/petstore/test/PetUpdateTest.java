package petstore.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import petstore.endpoint.PetEndpoint;
import petstore.model.CategoryModel;
import petstore.model.PetModel;
import petstore.model.TagModel;

public class PetUpdateTest {

    private PetEndpoint petEndpoint = new PetEndpoint();
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






}
