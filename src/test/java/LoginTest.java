import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import model.Credentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;

@RunWith(Parameterized.class)
public class LoginTest {
    private final Credentials credentials;
    private final int loginStatusCode;
    private final String message;
    private CourierClient courierClient;
    private final boolean isID;
    private int id;

    public LoginTest(Credentials credentials, int loginStatusCode, String message, boolean isID) {
        this.credentials = credentials;
        this.loginStatusCode = loginStatusCode;
        this.message = message;
        this.isID = isID;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {Credentials.fromLogStrPassStr ("noname8743","qwerty"), SC_OK, null, true},
                {Credentials.fromLogStrPassStr ("","qwerty"), SC_BAD_REQUEST, "Недостаточно данных для входа", false},
                {Credentials.fromLogStrPassStr ("noname8743",""), SC_BAD_REQUEST, "Недостаточно данных для входа", false},
                {Credentials.fromLogStrPassStr ("noname0000","qwerty"), SC_NOT_FOUND, "Учетная запись не найдена", false},
                {Credentials.fromLogStrPassStr ("noname8743","qwertyabc"), SC_NOT_FOUND, "Учетная запись не найдена", false},
                {Credentials.fromLogStrPassStr ("login199","password991"), SC_OK, null, true},
                {Credentials.fromLogStrPassStr ("randLog729","pass7827word"), SC_OK, null, true}
        };
    }

    @Before
    public void setUp(){
        id = 0;
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Курьер может авторизоваться")
    public void courierCanBeLoginTest() {
        ValidatableResponse responseLogin = courierClient.loginCourier(credentials);
        int loginStatusCodeActual = responseLogin.extract().statusCode();
        String messageActual = responseLogin.extract().path("message");
        Assert.assertEquals(loginStatusCode, loginStatusCodeActual);
        Assert.assertEquals(message, messageActual);
    }

    @Test
    @DisplayName("Успешный запрос возвращает id курьера")
    public  void successLoginReturnsIdTest () {
        ValidatableResponse responseLogin = courierClient.loginCourier(credentials);
        if (responseLogin.extract().statusCode() == SC_OK) {
            id = responseLogin.extract().path("id");
        }
        Assert.assertEquals(isID, id>0);
    }
}



