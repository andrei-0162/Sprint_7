package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.*;
import resources.constants.CreateDeleteCourierConstants;
import resources.constants.LoginConstants;
import resources.constants.TotalConstants;

import java.util.Random;


public class LoginTest {


    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = TotalConstants.BASE_URL;
    }

    @Test
    @DisplayName("Логин курьера")
    @Description("Создания курьера, логин курьера с проверкой статус-кода и тела ответа, удаление курьера")
    //курьер может авторизоваться;
    //успешный запрос возвращает id.
    public void loginCourier(){
        String login = TotalConstants.DEFAULT_LOGIN + random4Numbers();
        String password = TotalConstants.DEFAULT_PASSWORD;
        String name = TotalConstants.DEFAULT_NAME;

        CreateCourier newCreateCourier = new CreateCourier(login, password, name);
        Response response = newCreateCourier.createCourierPOST(newCreateCourier);
        newCreateCourier.checkStatusOfCreatedCourier(response, CreateDeleteCourierConstants.STATUS_CODE_201);

        LoginCourier newLogin = new LoginCourier(login, password);
        Response loginResponse = newLogin.loginOfCourierPOST(newLogin);
        newLogin.checkStatusOfLoggedInCourier(loginResponse, LoginConstants.LOGIN_STATUS_CODE_200);
        newLogin.checkBodyMessage(loginResponse, LoginConstants.LOGIN_BODY_KEY_NAME_FOR_CODE_200);

        LoginCourierDeserialization loggedCourier = loginResponse.as(LoginCourierDeserialization.class);
        Assert.assertNotNull(loggedCourier.getId());

        //Удаление курьера
        new DeleteCourier().deleteCourier(newCreateCourier);
    }

    @Test
    @DisplayName("Попытка логина курьера при незаполненных обязательных полях")
    @Description("Создания курьера, попытка логина без/с обязательных полей, удаление курьера")
    //для авторизации нужно передать все обязательные поля;
    //система вернёт ошибку, если неправильно указать логин или пароль;
    //если какого-то поля нет, запрос возвращает ошибку;
    public void tryLoginInvalidCourier(){
        String login = TotalConstants.DEFAULT_LOGIN + random4Numbers();
        String password = TotalConstants.DEFAULT_PASSWORD;
        String name = TotalConstants.DEFAULT_NAME;

        CreateCourier newCreateCourier = new CreateCourier(login, password, name);
        Response response = newCreateCourier.createCourierPOST(newCreateCourier);
        newCreateCourier.checkStatusOfCreatedCourier(response, CreateDeleteCourierConstants.STATUS_CODE_201);

        //(без логина)
        LoginCourier newLogin = new LoginCourier(TotalConstants.EMPTY_VALUE, password);
        Response loginResponse = newLogin.loginOfCourierPOST(newLogin);
        newLogin.checkStatusOfLoggedInCourier(loginResponse, LoginConstants.LOGIN_STATUS_CODE_400);
        newLogin.checkBodyMessage(loginResponse, LoginConstants.LOGIN_BODY_KEY_NAME_FOR_CODE_400, LoginConstants.LOGIN_BODY_MESSAGE_FOR_CODE_400);

        //(без пароля)
        newLogin = new LoginCourier(login, TotalConstants.EMPTY_VALUE);
        loginResponse = newLogin.loginOfCourierPOST(newLogin);
        newLogin.checkStatusOfLoggedInCourier(loginResponse, LoginConstants.LOGIN_STATUS_CODE_400);
        newLogin.checkBodyMessage(loginResponse, LoginConstants.LOGIN_BODY_KEY_NAME_FOR_CODE_400, LoginConstants.LOGIN_BODY_MESSAGE_FOR_CODE_400);

        //Удаление курьера
        new DeleteCourier().deleteCourier(newCreateCourier);
    }

    @Test
    @DisplayName("Попытка логина под несуществующим курьером")
    @Description("Попытка логина с невалидными кредами, проверка статус-кода и тела ответа")
    //система вернёт ошибку, если неправильно указать логин или пароль;
    //если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
    public void tryLoginNoExistCourier(){
        String login = TotalConstants.DEFAULT_LOGIN + random4Numbers();
        String password = TotalConstants.DEFAULT_PASSWORD;

        LoginCourier newLogin = new LoginCourier(login, password);
        Response loginResponse = newLogin.loginOfCourierPOST(newLogin);
        newLogin.checkStatusOfLoggedInCourier(loginResponse, LoginConstants.LOGIN_STATUS_CODE_404);
        newLogin.checkBodyMessage(loginResponse, LoginConstants.LOGIN_BODY_KEY_NAME_FOR_CODE_404, LoginConstants.LOGIN_BODY_MESSAGE_FOR_CODE_404);
    }


}
