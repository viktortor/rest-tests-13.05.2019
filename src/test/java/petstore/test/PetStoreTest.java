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
import static petstore.endpoint.PetEndpoint.*;

@Concurrent (threads = "4")
@RunWith(SerenityRunner.class)
public class PetStoreTest {


    @Steps //вычитываем степы
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
    public void getPetByIdTest(){
        int petId = petModel.getId();
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

//        PetModel petModel = new PetModel(
//                877,
//                 new CategoryModel(),
//                 "newmypet",
//                new String[]{"www.zoo.com"},
//                new TagModel[]{new TagModel()},
//                "AVAILABLE");

        petEndpoint
                .createPet(petModel)
                .statusCode(200);
    }


//        @Test
//        public void delPetByIdTest(){
//            int petId = petModel.getId();
//            petEndpoint
//                    .delPetById(petId)
//                    .statusCode(200);
//    }













}
