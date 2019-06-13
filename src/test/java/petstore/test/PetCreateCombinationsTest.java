package petstore.test;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoint.PetEndpoint;
import petstore.model.CategoryModel;
import petstore.model.PetModel;
import petstore.model.TagModel;

import java.util.Arrays;
import java.util.Collection;

@Concurrent //запустит тесты параллельно
@RunWith(SerenityParameterizedRunner.class)
public class PetCreateCombinationsTest {


    @Steps //вычитываем степы
    private PetEndpoint petEndpoint;

    @TestData
    public static Collection<Object[]> testDataCreatePet(){
        return Arrays.asList(new Object[][]{
                {"zverushka", 200},
                {"zverenysh", 200},
                {"zverushka1", 200},
                {"zverushka2", 200}
        });
    }


    private final String petName;
    private final int expectedStatusCode;


    public PetCreateCombinationsTest(String petName, int expectedStatusCode) {
        this.petName = petName;
        this.expectedStatusCode = expectedStatusCode;
    }



    @Test
    public void createPetNameCombinationsTest(){

        PetModel petModel = new PetModel(
                new CategoryModel(),
                petName,
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndpoint
                .createPet(petModel)
                .statusCode(expectedStatusCode);
    }















}
