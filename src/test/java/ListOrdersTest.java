import model.Order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.apache.http.HttpStatus.SC_OK;

public class ListOrdersTest {
    private OrderClient orderClient;

    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Список всех заказов")
    public void listOfAllOrdersTest(){
        List<Order> orders;
        int statusCodeExpected = SC_OK;
        ValidatableResponse responseListOrders = orderClient.getListOrders();
        int statusCodeActual = responseListOrders.extract().statusCode();
        orders = responseListOrders.extract().path("orders");
        Assert.assertEquals(statusCodeExpected, statusCodeActual);
        Assert.assertTrue(orders.size() > 0);
    }
}
