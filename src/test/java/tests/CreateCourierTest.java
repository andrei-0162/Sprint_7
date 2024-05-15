package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import resources.CreateCourier;
import resources.DeleteCourier;
import resources.Parameters;

import java.util.Random;

//DONE
public class CreateCourierTest {

    public  int random4Numbers () {
        return new Random().nextInt(8999)+1000;
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = Parameters.BASE_URL;
    }

    @Test
    @DisplayName("Проверка создания курьера")
    @Description("Создание курьера с проверкой кода и тела ответа")
    //курьера можно создать
    //запрос возвращает правильный код ответа
    //успешный запрос возвращает ok: true
    public void createCourier(){
        String login = Parameters.DEFAULT_LOGIN + random4Numbers();
        String password = Parameters.DEFAULT_PASSWORD;
        String name = Parameters.DEFAULT_NAME;

        CreateCourier newCreateCourier = new CreateCourier(login, password, name);
        Response response = newCreateCourier.createCourierPOST(newCreateCourier);
        newCreateCourier.checkStatusOfCreatedCourier(response, Parameters.STATUS_CODE_201);
        newCreateCourier.checkBodyMessage(response, Parameters.BODY_KEY_NAME_FOR_CODE_201, Parameters.BODY_MESSAGE_FOR_CODE_201);

        //Удаление курьера
        new DeleteCourier().deleteCourier(newCreateCourier);
    }

    @Test
    @DisplayName("Попытка создания двух одинаковых курьеров")
    @Description("Попытка создания второго курьера с проверкой кода и тела ответа")
    //нельзя создать двух одинаковых курьеров;
    //запрос возвращает правильный код ответа;
    //если создать пользователя с логином, который уже есть, возвращается ошибка.
    public void tryToCreateCourierTwice(){
        String login = Parameters.DEFAULT_LOGIN + random4Numbers();
        String password = Parameters.DEFAULT_PASSWORD;
        String name = Parameters.DEFAULT_NAME;

        CreateCourier newCreateCourier = new CreateCourier(login, password, name);
        newCreateCourier.createCourierPOST(newCreateCourier);
        Response response = newCreateCourier.createCourierPOST(newCreateCourier);
        newCreateCourier.checkStatusOfCreatedCourier(response, Parameters.STATUS_CODE_409);
        newCreateCourier.checkBodyMessage(response, Parameters.BODY_KEY_NAME_FOR_CODE_409, Parameters.BODY_MESSAGE_FOR_CODE_409);

        //Удаление курьера
        new DeleteCourier().deleteCourier(newCreateCourier);
    }

    @Test
    @DisplayName("Попытка создания курьеров без заполнения обязательных полей")
    @Description("Попытка создания курьеров с проверкой кода и тела ответа")
    //чтобы создать курьера, нужно передать в ручку все обязательные поля;
    //запрос возвращает правильный код ответа;
    //если одного из полей нет, запрос возвращает ошибку;
    public void tryToCreateInvalidCourier(){

        String login = Parameters.DEFAULT_LOGIN + random4Numbers();
        String password = Parameters.DEFAULT_PASSWORD;
        String name = Parameters.DEFAULT_NAME;

        // (без логина)
        CreateCourier newCreateCourier = new CreateCourier(Parameters.NULL_VALUE, password, name);
        Response response = newCreateCourier.createCourierPOST(newCreateCourier);
        newCreateCourier.checkStatusOfCreatedCourier(response, Parameters.STATUS_CODE_400);
        newCreateCourier.checkBodyMessage(response, Parameters.BODY_KEY_NAME_FOR_CODE_400, Parameters.BODY_MESSAGE_FOR_CODE_400);

        // (без пароля)
        newCreateCourier = new CreateCourier(login, Parameters.NULL_VALUE, name);
        response = newCreateCourier.createCourierPOST(newCreateCourier);
        newCreateCourier.checkStatusOfCreatedCourier(response, Parameters.STATUS_CODE_400);
        newCreateCourier.checkBodyMessage(response, Parameters.BODY_KEY_NAME_FOR_CODE_400, Parameters.BODY_MESSAGE_FOR_CODE_400);

        // (без имени)
        newCreateCourier = new CreateCourier(login, password, Parameters.NULL_VALUE);
        response = newCreateCourier.createCourierPOST(newCreateCourier);
        newCreateCourier.checkStatusOfCreatedCourier(response, Parameters.STATUS_CODE_201);
        newCreateCourier.checkBodyMessage(response, Parameters.BODY_KEY_NAME_FOR_CODE_201, Parameters.BODY_MESSAGE_FOR_CODE_201);

        //Удаление курьера
        new DeleteCourier().deleteCourier(newCreateCourier);
    }

}
