package petstore.test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoint.PetEndpoint;
import petstore.model.CategoryModel;
import petstore.model.PetModel;
import petstore.model.TagModel;
import static petstore.endpoint.PetEndpoint.*;

@RunWith(DataProviderRunner.class)
public class PetStoreTest {


    @Steps //вычитываем степы
    private PetEndpoint petEndpoint = new PetEndpoint();


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


    @DataProvider
    public static Object[][] createPetDataProvider() {
        return new Object[][] {
                {766, "Bronze", 200},
                {767, "Bronze", 200},
                {768, "Silver", 200},
                {769, "Silver", 200},
                {770, null, 200}
        };
    }



    @Test
    @UseDataProvider("createPetDataProvider")
    public void createPetTest(int petId, String petName, int expectedStatus){

        PetModel petModel = new PetModel(
                petId,
                 new CategoryModel(),
                 petName,
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");


        petEndpoint
                .createPet(petModel)
                .statusCode(expectedStatus);
    }


        @Test
        public void delPetByIdTest(){
            int petId = 766;
            petEndpoint
                    .delPetById(petId)
                    .statusCode(200);
    }













}
