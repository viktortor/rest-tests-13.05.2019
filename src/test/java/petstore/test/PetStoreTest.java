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
        int petId = 2;
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
                766,
                 new CategoryModel(),
                 "NewMyPet",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");


        petEndpoint
                .createPet(petModel)
                .statusCode(200);
    }


        @Test
        public void delPetByIdTest(){
            int petId = 1;
            petEndpoint
                    .delPetById(petId)
                    .statusCode(200);
    }


















    ////////////////////////////////////Homework about update Pet///////////////////////////////////

//
//
//
//    public void createPet(int id, String name, String[] photoUrls, String status){
//        PetModel petModel = new PetModel(
//                id,
//                new CategoryModel(),
//                name,
//                photoUrls,
//                new TagModel[]{new TagModel()},
//                status);
//
//
//
//        RestAssured.given()
//                 .header("Content-Type", "application/json")
//                .contentType("application/json")
//                .body(petModel)
//                .log().uri()
//                .post(Config.CREATE_PET)
//
//                .then()
////                .log().all()
//                .statusCode(200);
//    }
//
//
//    public JSONObject getPetById(int petId){
//
//        ValidatableResponse response = RestAssured.given()
//                //.log().uri()
//                .get(Config.GET_PET_BY_ID,petId)
//                .then()
////                .log().all()
//                .statusCode(200);
//        JSONObject jsonObject = new JSONObject(response.extract().asString());
//
//       return jsonObject;
//
//    }
//
//
//    public void delPetById(int petId){
//
//        RestAssured.given()
//                .log().uri()
//                .delete(Config.DEL_PET_BY_ID,petId)
//                .then()
////                .log().all()
//                .statusCode(200);
//
//    }
//
//
//    public void updatePet(int id, String name, String[] photoUrls, String status){
//        PetModel petModel = new PetModel(
//                id,
//                new CategoryModel(),
//                name,
//                photoUrls,
//                new TagModel[]{new TagModel()},
//                status);
//
//
//
//        RestAssured.given()
//                // .header("Content-Type", "application/json")
//                .contentType("application/json")
//                .body(petModel)
//                // .log().uri()
//                .post(Config.UPDATE_PET)
//        .then()
////        .log().all()
//         .statusCode(200);
//    }
//
//
//    @Test
//    public void updatePetTest(){
//        int petId = 764;
//        String petNameForCreating = "vik3";
//        String[] photoUrlsForCreating = new String[]{"www.eee.com"};
//        String petStatusForCreating = "AVAILABLE";
//
//        String petNameForUpdating = "vik4";
//        String[] photoUrlsForUpdating = new String[]{"www.eee.com","www.rrr.com"};
//        String petStatusForUpdating = "AVAILABLE";
//
//
//        createPet(petId, petNameForCreating, photoUrlsForCreating, petStatusForCreating);
//        updatePet (petId, petNameForUpdating, photoUrlsForUpdating, petStatusForUpdating);
//        JSONObject getPetByIdResponse = getPetById(petId);
//
//
//        int idFromResponse = getPetByIdResponse.getInt("id");
//        Assert.assertEquals(petId, idFromResponse);
//
//        String petNameFromResponse = getPetByIdResponse.getString("name");
//        Assert.assertEquals(petNameForUpdating, petNameFromResponse);
//
//
//        JSONArray photoUrlsFromResponse = getPetByIdResponse.getJSONArray("photoUrls");
//        if (photoUrlsFromResponse.length()==photoUrlsForUpdating.length){
//
//            for (int i = 0; i < photoUrlsFromResponse.length(); ++i) {
//                Assert.assertEquals(photoUrlsFromResponse.get(i),photoUrlsForUpdating[i]);
//            }
//        }
//
//        String petStatusFromResponse = getPetByIdResponse.getString("status");
//        Assert.assertEquals(petStatusForUpdating, petStatusFromResponse);
//
//        delPetById(petId);
//
//    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
