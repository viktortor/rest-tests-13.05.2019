package petstore.test;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoint.OrderEndpoint;
import petstore.model.OrderModel;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import static org.hamcrest.CoreMatchers.is;

@Concurrent
@RunWith(SerenityRunner.class)
public class OrderCreateTest {
    @Steps
    private OrderEndpoint orderEndpoint;
    private OrderModel orderModel;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSxxx" );
    private String currentDate = OffsetDateTime.now(ZoneOffset.UTC).format(dateFormat) ;
    private String date = currentDate.substring(0, currentDate.indexOf('+')) + "+0000";


    @Before
    public void preCondition(){

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
