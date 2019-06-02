package petstore.endpoint;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import petstore.model.OrderModel;


public class OrderEndpoint {

    private RequestSpecification given(){
        return RestAssured.given()
                .baseUri(Config.BASE_URI)
                .contentType("application/json")
                .log().uri();
    }


    public ValidatableResponse createOrder(OrderModel orderModel){

        return  given()
                .body(orderModel)
                .post(Config.CREATE_ORDER)
                .then()
                .log().all();
    }



    public ValidatableResponse getOrderById(int orderId) {
        return given()
                .get(Config.GET_ORDER_BY_ID, orderId)
                .then()
                .log().all()
                .statusCode(200);
    }


    public ValidatableResponse delOrderById(int orderId){

        return given()
                .delete(Config.DEL_ORDER_BY_ID,orderId)
                .then()
                .log().all();
    }





}
