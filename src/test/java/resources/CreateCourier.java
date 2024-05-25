package resources;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import resources.constants.CreateDeleteCourierConstants;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourier {
    private String login;
    private String password;
    private String firstName;

    public CreateCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public CreateCourier() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Step("Отправка запроса POST на Создание курьера")
    public Response createCourierPOST (CreateCourier newCreateCourier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(newCreateCourier)
                .when()
                .post(CreateDeleteCourierConstants.CREATE_COURIER_ENDPOINT);
    }

    @Step("Проверка статуса кода")
    public void checkStatusOfCreatedCourier(Response response, int statusCode) {
        response.then().assertThat()
                .and()
                .statusCode(statusCode);
    }

    @Step("Проверка тела сообщения по ключу и значению")
    public void checkBodyMessage(Response response, String keyName, String expectedMessage) {
        response.then()
                .assertThat()
                .body(keyName, equalTo(expectedMessage));
    }
    @Step("Проверка тела сообщения по ключу и значению")
    public void checkBodyMessage(Response response, String keyName, Boolean expectedMessage) {
        response.then()
                .assertThat()
                .body(keyName, equalTo(expectedMessage));
    }


}
