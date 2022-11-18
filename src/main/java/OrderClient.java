import io.qameta.allure.Step;
import model.Order;
import model.Track;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    private final String PATH_ORDERS =  "/api/v1/orders";
    private final String PATH_CANCEL =  "/api/v1/orders/cancel?track=";

    @Step ("Создать заказ")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(PATH_ORDERS)
                .then();
    }

    @Step ("Отменить заказ")
    public ValidatableResponse cancelOrder(Track track) {
        return given()
                .spec(getSpec())
                .when()
                .put(PATH_CANCEL + track.getTrack())
                .then();
    }

    @Step ("Получить список всех заказов")
    public ValidatableResponse getListOrders() {
        return given()
                .spec(getSpec())
                .when()
                .get(PATH_ORDERS)
                .then();
    }
}
