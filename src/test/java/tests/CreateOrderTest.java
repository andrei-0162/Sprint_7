package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import resources.CreateOrder;
import resources.Parameters;


//DONE
@RunWith(Parameterized.class)
public class CreateOrderTest {
    private String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }


    //параметризация для теста CreateOrderTest
    @Parameterized.Parameters
    public static Object [][] testDataForDoesHaveManeLionTest() {
        return new Object[][]{
                {new String[] {Parameters.BLACK_COLOR}},
                {new String[] {Parameters.GREY_COLOR}},
                {new String[] {}},
                {new String[] {Parameters.BLACK_COLOR, Parameters.GREY_COLOR}}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = Parameters.BASE_URL;
    }

    @Test
    @DisplayName("Создание заказа с различным выбором цвета")
    @Description("Создания заказа с проверкой кода и тела ответа")
    public void CreateOrderTest(){
        CreateOrder newCreateOrder = new CreateOrder(
                Parameters.FIRSTNAME,
                Parameters.LASTNAME,
                Parameters.ADDRESS,
                Parameters.METRO_STATION,
                Parameters.PHONE,
                Parameters.RENT_TIME,
                Parameters.DELIVERY_DATE,
                Parameters.COMMENT,
                color);
        Response response = newCreateOrder.createOrderPOST(newCreateOrder);
        newCreateOrder.checkStatusOfCreatedOrder(response, Parameters.CREATE_ORDER_STATUS_CODE_201);
        newCreateOrder.checkBodyMessage(response, Parameters.CREATE_ORDER_BODY_KEY_NAME_FOR_CODE_201);
    }

}
