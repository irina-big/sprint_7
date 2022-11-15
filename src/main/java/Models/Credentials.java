package Models;

public class Credentials {
    private String login;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Credentials from(Courier courier) {
        return new Credentials(courier.getLogin(), courier.getPassword());
    }

    public static Credentials fromLogStrPassStr(String login, String password) {
        return new Credentials(login, password);
    }
}
