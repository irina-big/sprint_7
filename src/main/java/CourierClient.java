
import io.restassured.response.ValidatableResponse;
import Models.Courier;
import Models.Credentials;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {
    private final String PATH_COURIER =  "/api/v1/courier";
    private final String PATH_LOGIN = "/api/v1/courier/login";
    private final String PATH_DELETE = "/api/v1/courier/";
    public ValidatableResponse create(Courier courier){
        return  given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(PATH_COURIER)
                .then();
    }
    public ValidatableResponse login(Credentials credentials){
        return  given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH_LOGIN)
                .then();
    }
    public ValidatableResponse delete(int id){
        return given()
                .spec(getSpec())
                .when()
                .delete(PATH_DELETE + id)
                .then();
    }
}
