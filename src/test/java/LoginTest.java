

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import Models.Credentials;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
                {Credentials.fromLogStrPassStr ("noname8743","qwerty"), 200, null, true},
                {Credentials.fromLogStrPassStr ("","qwerty"), 400, "Недостаточно данных для входа", false},
                {Credentials.fromLogStrPassStr ("noname8743",""), 400, "Недостаточно данных для входа", false},
                {Credentials.fromLogStrPassStr ("noname0000","qwerty"), 404, "Учетная запись не найдена", false},
                {Credentials.fromLogStrPassStr ("noname8743","qwertyabc"), 404, "Учетная запись не найдена", false},
                {Credentials.fromLogStrPassStr ("login199","password991"), 200, null, true},
                {Credentials.fromLogStrPassStr ("randLog729","pass7827word"), 200, null, true}
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
        ValidatableResponse responseLogin = courierClient.login(credentials);
        int loginStatusCodeActual = responseLogin.extract().statusCode();
        String messageActual = responseLogin.extract().path("message");
        Assert.assertEquals(loginStatusCode, loginStatusCodeActual);
        Assert.assertEquals(message, messageActual);
    }

    @Test
    @DisplayName("Успешный запрос возвращает id курьера")
    public  void successLoginReturnsIdTest () {
        ValidatableResponse responseLogin = courierClient.login(credentials);
        if (responseLogin.extract().statusCode() == 200) {
            id = responseLogin.extract().path("id");
        }
        Assert.assertEquals(isID, id>0);
    }
}



