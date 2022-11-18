import generator.OrderGenerator;
import model.Colors;
import model.Order;
import model.Track;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;

@RunWith(Parameterized.class)

public class OrderTest {
    private int statusCode;
    private Track track;
    private final Order order;
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
        if (statusCode == SC_CREATED) {
            responseCancel = orderClient.cancelOrder(track);
            okRes = responseCancel.extract().path("ok");
        }
        Assert.assertEquals(okRes, true);
    }

    @Test
    @DisplayName("Заказ может быть создан с указанием цвета или без него")
    public void orderCanBeCreatedTest(){
        ValidatableResponse responseCreate = orderClient.createOrder(order);
        statusCode = responseCreate.extract().statusCode();
        track = new Track(responseCreate.extract().path("track"));
        Assert.assertEquals(statusCode, SC_CREATED);
        Assert.assertTrue(track.getTrack()> 0);
    }
}
