
package Generators;

import Models.Courier;

import java.util.Random;

public class CourierGenerator {
    public static Courier getDefault(){
        return  new Courier("default_login", "default_password", "Владимир");
    }

    public static Courier getCourierWithoutLogin() {
        return new Courier("", "default_password", "Владимир");
    }
    public static Courier getCourierWithoutPassword() {
        return new Courier("default_login", "", "Владимир");
    }
    public static Courier getCourierWithRandomData() {
        Random random = new Random();
        return new Courier("login" + random.nextInt(1000), "password" + random.nextInt(1000), "Alexandr");
    }
    public static Courier getCourierFromParams(String login, String password, String firstName) {
        return new Courier(login, password, firstName);
    }
}
