package resources;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOrder {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    public CreateOrder(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public CreateOrder() {
    }
@Step("Отправка POST запроса на Создание Заказа")
    public Response createOrderPOST (CreateOrder newCreateOrder) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(newCreateOrder)
                .when()
                .post(Parameters.CREATE_ORDER_ENDPOINT);
    }

    @Step("Проверка статус кода")
    public void checkStatusOfCreatedOrder(Response response, int statusCode) {
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

}
