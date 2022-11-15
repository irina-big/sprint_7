import Generators.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import Models.Courier;
import Models.Credentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CourierTest {
    private final Courier courier;
    private final int createStatusCode;
    private final boolean okResult;
    private final String message;

    private CourierClient courierClient;

    public CourierTest(Courier courier, int createStatusCode,  boolean okResult, String message) {
        this.courier = courier;
        this.createStatusCode = createStatusCode;
        this.okResult = okResult;
        this.message = message;
    }
    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {CourierGenerator.getDefault(), 201, true, null},
                {CourierGenerator.getCourierWithRandomData(), 201, true, null},
                {CourierGenerator.getCourierWithoutLogin(), 400, false, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getCourierWithoutPassword(), 400, false, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator .getCourierFromParams("login199", "0987654321", "Александр"), 409, false, "Этот логин уже используется. Попробуйте другой."},
                {CourierGenerator .getCourierFromParams("randLog729", "0987654321", "Александр"), 409, false, "Этот логин уже используется. Попробуйте другой."}
        };
    }

    @Before
    public void setUp(){
        courierClient = new CourierClient();
    }

    @After
    public void cleanUp(){
        ValidatableResponse responseLogin = courierClient.login(Credentials.from(courier));
        if (responseLogin.extract().statusCode() == 200) {
            int id = responseLogin.extract().path("id");
            courierClient.delete(id);
        }
    }

    @Test
    @DisplayName("Курьера можно создать, заполнив все поля")
    public void courierCanBeCreatedTest() {
        ValidatableResponse responseCreate = courierClient.create(courier);
        int createStatusCodeActual = responseCreate.extract().statusCode();
        String messageActual = responseCreate.extract().path("message");
        Assert.assertEquals(createStatusCode, createStatusCodeActual);
        Assert.assertEquals(messageActual, message);
    }

    @Test
    @DisplayName("Успешное создание курьера возвращает ok:true")
    public  void courierSuccessCreatedReturnsOkTest(){
        ValidatableResponse responseCreate = courierClient.create(courier);
        int createStatusCodeActual = responseCreate.extract().statusCode();
        boolean okResultActual = false;
        if (createStatusCodeActual == 201) {
            okResultActual = responseCreate.extract().path("ok");
        }
        Assert.assertEquals(okResultActual, okResult);
    }
}

