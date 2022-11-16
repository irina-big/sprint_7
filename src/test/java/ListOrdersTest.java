

import Models.Order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        int statusCodeExpected = 200;
        ValidatableResponse responseListOrders = orderClient.listOrders();
        int statusCodeActual = responseListOrders.extract().statusCode();
        orders = responseListOrders.extract().path("orders");
        Assert.assertEquals( statusCodeExpected, statusCodeActual);
        Assert.assertTrue(orders.size() > 0);
    }
}
