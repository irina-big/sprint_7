import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.Courier;
import model.Credentials;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {
    private final String PATH_COURIER =  "/api/v1/courier";
    private final String PATH_LOGIN = "/api/v1/courier/login";

    @Step ("Создать курьера")
    public ValidatableResponse createCourier(Courier courier){
        return  given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(PATH_COURIER)
                .then();
    }

    @Step ("Авторизоваться в системе")
    public ValidatableResponse loginCourier(Credentials credentials){
        return  given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH_LOGIN)
                .then();
    }

    @Step ("Удалить курьера")
    public ValidatableResponse deleteCourier(int id){
        return given()
                .spec(getSpec())
                .when()
                .delete(PATH_COURIER +"/" + id)
                .then();
    }
}
