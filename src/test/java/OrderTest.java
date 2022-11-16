

import Generators.OrderGenerator;
import Models.Colors;
import Models.Order;
import Models.Track;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)

public class OrderTest {
    private int statusCode;
    private Track track;
    private Order order;
    private OrderClient orderClient;

    public OrderTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {OrderGenerator.getOrderWithoutColor()},
                {OrderGenerator.getOrderWithOneColor(Colors.BLACK)},
                {OrderGenerator.getOrderWithOneColor(Colors.GRAY)},
                {OrderGenerator.getOrderWithTwoColors()},
                {OrderGenerator.getOrderSomeDataWithTwoColors()}
        };
    }

    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }

    @After
    public void cancelOrder(){
        ValidatableResponse responseCancel;
        boolean okRes = false;
        if (statusCode == 201) {
            responseCancel = orderClient.cancel(track);
            okRes = responseCancel.extract().path("ok");
        }
        Assert.assertEquals(okRes, true);
    }

    @Test
    @DisplayName("Заказ может быть создан с указанием цвета или без него")
    public void orderCanBeCreatedTest(){
        ValidatableResponse responseCreate = orderClient.create(order);
        statusCode = responseCreate.extract().statusCode();
        track = new Track(responseCreate.extract().path("track"));
        Assert.assertEquals(statusCode, 201);
        Assert.assertTrue(track.getTrack()> 0);
    }
}
