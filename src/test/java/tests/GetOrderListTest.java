package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.*;

public class GetOrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = Parameters.BASE_URL;
    }

    @Test
    @DisplayName("Проверка получения списка заказов")
    @Description("Проверка получения списка заказов")
    //Проверь, что в тело ответа возвращается список заказов.
    public void getOrderListTest () {
        GetOrderList newOrderList = new GetOrderList();
        Response response = newOrderList.getOrderList();
        Assert.assertNotNull(response.body().asString());
    }
}
