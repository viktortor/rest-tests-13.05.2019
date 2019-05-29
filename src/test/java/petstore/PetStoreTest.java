package petstore;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import netscape.javascript.JSObject;
import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;
import org.json.JSONObject;
import java.lang.reflect.Array;


public class PetStoreTest {

    static {
        RestAssured.baseURI = Config.BASE_URI;
    }

    private enum Status {
        AVAILABLE,
        PENDING,
        SOLD
    }

    @Test
    public void getPetByIdTest(){
        int petId = 760;

        RestAssured.given()
                 //.log().uri()
                .get(Config.GET_PET_BY_ID,petId)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getPetByStatusTest() {

        for (Status status : Status.values()) {

             RestAssured.given()
                    .param("status", status)
                    .log().uri()
                    .get(Config.GET_PET_BY_STATUS)

                    .then()
                    .log().all()
                    .statusCode(200);
}

    }


    @Test
    public void createPetTest(){
        PetModel petModel = new PetModel(
                323,
                 new CategoryModel(),
                 "NewMyPet",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");


        RestAssured.given()
               // .header("Content-Type", "application/json")
                .contentType("application/json")
                .body(petModel)
                .log().uri()
                .post(Config.CREATE_PET)

                .then()
                .log().all()
                .statusCode(200);
    }


        @Test
        public void delPetByIdTest(){
            int petId = 1;

            RestAssured.given()
                    //.log().uri()
                    .delete(Config.DEL_PET_BY_ID,petId)
                    .then()
                    .log().all()
                    .statusCode(200);

    }
///////////////////////////////////////////////////////////////////////




    public void createPet(int id, String name, String[] photoUrls, String status){
        PetModel petModel = new PetModel(
                id,
                new CategoryModel(),
                name,
                photoUrls,
                new TagModel[]{new TagModel()},
                status);



        RestAssured.given()
                 .header("Content-Type", "application/json")
                .contentType("application/json")
                .body(petModel)
                .log().uri()
                .post(Config.CREATE_PET)

                .then()
//                .log().all()
                .statusCode(200);
    }


    public JSONObject getPetById(int petId){

        ValidatableResponse response = RestAssured.given()
                //.log().uri()
                .get(Config.GET_PET_BY_ID,petId)
                .then()
//                .log().all()
                .statusCode(200);
        JSONObject jsonObject = new JSONObject(response.extract().asString());

       return jsonObject;

    }


    public void delPetById(int petId){

        RestAssured.given()
                .log().uri()
                .delete(Config.DEL_PET_BY_ID,petId)
                .then()
//                .log().all()
                .statusCode(200);

    }


    public void updatePet(int id, String name, String[] photoUrls, String status){
        PetModel petModel = new PetModel(
                id,
                new CategoryModel(),
                name,
                photoUrls,
                new TagModel[]{new TagModel()},
                status);



        RestAssured.given()
                // .header("Content-Type", "application/json")
                .contentType("application/json")
                .body(petModel)
                // .log().uri()
                .post(Config.UPDATE_PET)
        .then()
//        .log().all()
         .statusCode(200);
    }


    @Test
    public void updatePetTest(){
        int petId = 764;
        String petNameForCreating = "vik3";
        String[] photoUrlsForCreating = new String[]{"www.eee.com"};
        String petStatusForCreating = "AVAILABLE";

        String petNameForUpdating = "vik4";
        String[] photoUrlsForUpdating = new String[]{"www.eee.com","www.rrr.com"};
        String petStatusForUpdating = "AVAILABLE";


        createPet(petId, petNameForCreating, photoUrlsForCreating, petStatusForCreating);
        updatePet (petId, petNameForUpdating, photoUrlsForUpdating, petStatusForUpdating);
        JSONObject getPetByIdResponse = getPetById(petId);


        int idFromResponse = getPetByIdResponse.getInt("id");
        Assert.assertEquals(petId, idFromResponse);

        String petNameFromResponse = getPetByIdResponse.getString("name");
        Assert.assertEquals(petNameForUpdating, petNameFromResponse);

//        JSONArray photoUrlsFromResponse = getPetByIdResponse.getJSONArray("photoUrls");
//        System.out.println(photoUrlsFromResponse);
//
//
//        for (int i = 0; i < photoUrlsFromResponse.length(); ++i) {
//            String photoUrlFromResponse = photoUrlsFromResponse.getString("");
//            String loc = rec.getString("loc");
////
//        String photoUrlsFromResponse = getPetByIdResponse..getString("photoUrls");
//        System.out.println("+++++++++++++++++++++++++++++"+photoUrlsFromResponse);
//        Assert.assertEquals(photoUrlsForUpdating, photoUrlsFromResponse);

        String petStatusFromResponse = getPetByIdResponse.getString("status");
        Assert.assertEquals(petStatusForUpdating, petStatusFromResponse);

        delPetById(petId);

    }



}
