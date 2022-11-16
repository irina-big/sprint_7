

import Models.Order;
import Models.Track;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class OrderClient extends Client {

    private final String PATH_ORDERS =  "/api/v1/orders";
    private final String PATH_CANCEL =  "/api/v1/orders/cancel?track=";

    public ValidatableResponse create(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(PATH_ORDERS)
                .then();
    }
    public ValidatableResponse cancel(Track track) {
        return given()
                .spec(getSpec())
                .when()
                .put(PATH_CANCEL + track.getTrack())
                .then();
    }
    public ValidatableResponse listOrders() {
        return given()
                .spec(getSpec())
                .when()
                .get(PATH_ORDERS)
                .then();
    }
}
