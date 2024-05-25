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
import resources.constants.TotalConstants;
import resources.constants.CreateOrderConstants;



@RunWith(Parameterized.class)
public class CreateOrderTest {
    private String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }


    //параметризация для теста CreateOrderTest
    @Parameterized.Parameters
    public static Object [][] testDataForCreateOrderTest() {
        return new Object[][]{
                {new String[] {CreateOrderConstants.BLACK_COLOR}},
                {new String[] {CreateOrderConstants.GREY_COLOR}},
                {new String[] {}},
                {new String[] {CreateOrderConstants.BLACK_COLOR, CreateOrderConstants.GREY_COLOR}}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = TotalConstants.BASE_URL;
    }

    @Test
    @DisplayName("Создание заказа с различным выбором цвета")
    @Description("Создания заказа с проверкой кода и тела ответа")
    public void createOrderTest(){
        CreateOrder newCreateOrder = new CreateOrder(
                CreateOrderConstants.FIRSTNAME,
                CreateOrderConstants.LASTNAME,
                CreateOrderConstants.ADDRESS,
                CreateOrderConstants.METRO_STATION,
                CreateOrderConstants.PHONE,
                CreateOrderConstants.RENT_TIME,
                CreateOrderConstants.DELIVERY_DATE,
                CreateOrderConstants.COMMENT,
                color);
        Response response = newCreateOrder.createOrderPOST(newCreateOrder);
        newCreateOrder.checkStatusOfCreatedOrder(response, CreateOrderConstants.CREATE_ORDER_STATUS_CODE_201);
        newCreateOrder.checkBodyMessage(response, CreateOrderConstants.CREATE_ORDER_BODY_KEY_NAME_FOR_CODE_201);
    }

}
