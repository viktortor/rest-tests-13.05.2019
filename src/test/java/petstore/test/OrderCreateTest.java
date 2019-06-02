package petstore.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import petstore.endpoint.OrderEndpoint;
import petstore.model.OrderModel;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

public class OrderCreateTest {

    private OrderEndpoint orderEndpoint = new OrderEndpoint();
    private OrderModel orderModel;
    private Date date;

    @Before
    public void preCondition(){
        date = new Date();

        orderModel = new OrderModel(
                9,
                323,
                3,
                date,
                "placed",
                false);

    }


    @After
    public void postCondition(){
        orderEndpoint
                .delOrderById(orderModel.getId())
                .statusCode(200);

    }


    @Test
    public void createOrderTest(){
        orderEndpoint
                .createOrder(orderModel)
                .statusCode(200);

        orderEndpoint
                .getOrderById(orderModel.getId())
                .statusCode(200)
                .body("petId", is(orderModel.getPetId()))
                .body("quantity", is(orderModel.getQuantity()))
                .body("shipDate", is(orderModel.getShipDate()))
                .body("status", is(orderModel.getStatus()))
                .body("complete", is(orderModel.isComplete()));

    }


}
