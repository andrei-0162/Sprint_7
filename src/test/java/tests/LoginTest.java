package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resources.*;

import java.util.Random;

//DONE

public class LoginTest {


    public int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = Parameters.BASE_URL;
    }

    @Test
    @DisplayName("Логин курьера")
    @Description("Создания курьера, логин курьера с проверкой статус-кода и тела ответа, удаление курьера")
    //курьер может авторизоваться;
    //успешный запрос возвращает id.
    public void LoginCourier(){
        String login = Parameters.DEFAULT_LOGIN + random4Numbers();
        String password = Parameters.DEFAULT_PASSWORD;
        String name = Parameters.DEFAULT_NAME;

        CreateCourier newCreateCourier = new CreateCourier(login, password, name);
        Response response = newCreateCourier.createCourierPOST(newCreateCourier);
        newCreateCourier.checkStatusOfCreatedCourier(response, Parameters.STATUS_CODE_201);

        LoginCourier newLogin = new LoginCourier(login, password);
        Response loginResponse = newLogin.loginOfCourierPOST(newLogin);
        newLogin.checkStatusOfLoggedInCourier(loginResponse, Parameters.LOGIN_STATUS_CODE_200);
        newLogin.checkBodyMessage(loginResponse, Parameters.LOGIN_BODY_KEY_NAME_FOR_CODE_200);

        LoginCourierDeserealization loggedCourier = loginResponse.as(LoginCourierDeserealization.class);
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
        String login = Parameters.DEFAULT_LOGIN + random4Numbers();
        String password = Parameters.DEFAULT_PASSWORD;
        String name = Parameters.DEFAULT_NAME;

        CreateCourier newCreateCourier = new CreateCourier(login, password, name);
        Response response = newCreateCourier.createCourierPOST(newCreateCourier);
        newCreateCourier.checkStatusOfCreatedCourier(response, Parameters.STATUS_CODE_201);

        //(без логина)
        LoginCourier newLogin = new LoginCourier(Parameters.EMPTY_VALUE, password);
        Response loginResponse = newLogin.loginOfCourierPOST(newLogin);
        newLogin.checkStatusOfLoggedInCourier(loginResponse, Parameters.LOGIN_STATUS_CODE_400);
        newLogin.checkBodyMessage(loginResponse, Parameters.LOGIN_BODY_KEY_NAME_FOR_CODE_400, Parameters.LOGIN_BODY_MESSAGE_FOR_CODE_400);

        //(без пароля)
        newLogin = new LoginCourier(login, Parameters.EMPTY_VALUE);
        loginResponse = newLogin.loginOfCourierPOST(newLogin);
        newLogin.checkStatusOfLoggedInCourier(loginResponse, Parameters.LOGIN_STATUS_CODE_400);
        newLogin.checkBodyMessage(loginResponse, Parameters.LOGIN_BODY_KEY_NAME_FOR_CODE_400, Parameters.LOGIN_BODY_MESSAGE_FOR_CODE_400);

        //Удаление курьера
        new DeleteCourier().deleteCourier(newCreateCourier);
    }

    @Test
    @DisplayName("Попытка логина под несуществующим курьером")
    @Description("Попытка логина с невалидными кредами, проверка статус-кода и тела ответа")
    //система вернёт ошибку, если неправильно указать логин или пароль;
    //если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
    public void tryLoginNoExistCourier(){
        String login = Parameters.DEFAULT_LOGIN + random4Numbers();
        String password = Parameters.DEFAULT_PASSWORD;

        LoginCourier newLogin = new LoginCourier(login, password);
        Response loginResponse = newLogin.loginOfCourierPOST(newLogin);
        newLogin.checkStatusOfLoggedInCourier(loginResponse, Parameters.LOGIN_STATUS_CODE_404);
        newLogin.checkBodyMessage(loginResponse, Parameters.LOGIN_BODY_KEY_NAME_FOR_CODE_404, Parameters.LOGIN_BODY_MESSAGE_FOR_CODE_404);
    }


}
