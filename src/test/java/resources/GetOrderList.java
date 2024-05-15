package resources;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetOrderList {

    private int  courierId;
    private String  nearestStation;
    private int limit;
    private int page;


    public GetOrderList(int courierId, String nearestStation, int limit, int page) {
        this.courierId = courierId;
        this.nearestStation = nearestStation;
        this.limit = limit;
        this.page = page;
    }

    public GetOrderList() {
    }
    @Step("Отправка GET запроса на получение Списка Заказов")
    public Response getOrderList () {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .get(Parameters.GET_ORDER_LIST_ENDPOINT);
    }


}
