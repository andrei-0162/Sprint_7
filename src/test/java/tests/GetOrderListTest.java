package tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.*;
import resources.constants.GetOrderConstants;
import resources.constants.TotalConstants;

import static org.hamcrest.Matchers.greaterThan;

public class GetOrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = TotalConstants.BASE_URL;
    }

    @Test
    @DisplayName("Проверка получения списка заказов")
    @Description("Проверка получения списка заказов на наличие хотя бы одного заказа")
    //Проверь, что в тело ответа возвращается список заказов.
    public void getOrderListTest () {
        GetOrderList newOrderList = new GetOrderList();
        Response responseGetOrderList = newOrderList.getOrderList();
        newOrderList.checkStatusOfGetOrderList(responseGetOrderList, GetOrderConstants.GET_ORDER_LIST_STATUS_CODE_200);
        //Проверка что в тело ответа возвращается список заказов.
        //Проверка списка заказов на наличие хотя бы одного заказа
        JsonObject convertedObject = new Gson().fromJson(responseGetOrderList.asString(), JsonObject.class);
        Assert.assertThat(convertedObject.get(GetOrderConstants.GET_ORDER_LIST_BODY_KEY_ORDER).getAsJsonArray().size(),greaterThan(GetOrderConstants.GET_ORDER_LIST_CHECKING_ORDER_COUNT));
    }
}
