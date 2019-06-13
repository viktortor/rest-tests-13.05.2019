package petstore.test;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoint.PetEndpoint;
import petstore.model.CategoryModel;
import petstore.model.PetModel;
import petstore.model.TagModel;
import static petstore.endpoint.PetEndpoint.*;

@RunWith(SerenityRunner.class)
public class PetStoreTest {


    @Steps //вычитываем степы
    private PetEndpoint petEndpoint;


    @Test
    public void getPetByIdTest(){
        int petId = 766;
        petEndpoint
                .getPetById(petId)
                .statusCode(200);
    }

    @Test
    public void getPetByStatusTest() {
        for (Status status : Status.values()) {
        petEndpoint
           .getPetByStatus(status)
           .statusCode(200);
}

    }


    @Test
    public void createPetTest(){

        PetModel petModel = new PetModel(
                877,
                 new CategoryModel(),
                 "newmypet",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndpoint
                .createPet(petModel)
                .statusCode(200);
    }


        @Test
        public void delPetByIdTest(){
            int petId = 766;
            petEndpoint
                    .delPetById(petId)
                    .statusCode(200);
    }













}
