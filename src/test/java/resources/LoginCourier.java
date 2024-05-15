package resources;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourier {


    private String login;
    private String password;
    public LoginCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginCourier() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Response loginOfCourierPOST (LoginCourier newLogin) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(newLogin)
                .when()
                .post(Parameters.LOGIN_COURIER_ENDPOINT);
    }
    @Step("Проверка статус кода")
    public void checkStatusOfLoggedInCourier(Response response, int statusCode) {
        response.then().assertThat()
                .and()
                .statusCode(statusCode);
    }
    @Step("Проверка тела сообщения по ключу и значению")
    public void checkBodyMessage(Response response, String keyName) {
        response.then()
                .assertThat()
                .body(keyName, notNullValue());
    }
    @Step("Проверка тела сообщения по ключу и значению")
    public void checkBodyMessage(Response response, String keyName, String expectedMessage) {
        response.then()
                .assertThat()
                .body(keyName, equalTo(expectedMessage));
    }



}
