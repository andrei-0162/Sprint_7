package resources;

import java.util.Date;
import java.util.Random;

public class Parameters {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    public static final String DEFAULT_LOGIN = "Maison";
    public static final String DEFAULT_PASSWORD = "123456";
    public static final String DEFAULT_NAME = "Power";

    public static final String EMPTY_VALUE = "";
    public static final String NULL_VALUE = null;


    //контсанты "Создание курьера в системе"
    public static final String CREATE_COURIER_ENDPOINT = "/api/v1/courier";

    public static final int STATUS_CODE_201 = 201;
    public static final String BODY_KEY_NAME_FOR_CODE_201 = "ok";
    public static final boolean BODY_MESSAGE_FOR_CODE_201 = true;

    public static final int STATUS_CODE_400 = 400;
    public static final String BODY_KEY_NAME_FOR_CODE_400 = "message";
    public static final String BODY_MESSAGE_FOR_CODE_400 = "Недостаточно данных для создания учетной записи";

    public static final int STATUS_CODE_409 = 409;
    public static final String BODY_KEY_NAME_FOR_CODE_409 = "message";
    public static final String BODY_MESSAGE_FOR_CODE_409 = "Этот логин уже используется. Попробуйте другой.";

    //контсанты "Удаление курьера в системе"
    public static final String DELETE_COURIER_ENDPOINT = "/api/v1/courier/";

    //контсанты "Логин курьера в системе"
    public static final String LOGIN_COURIER_ENDPOINT = "/api/v1/courier/login";
    public static final int LOGIN_STATUS_CODE_200 = 200;
    public static final String LOGIN_BODY_KEY_NAME_FOR_CODE_200 = "id";

    public static final int  LOGIN_STATUS_CODE_400 = 400;
    public static final String  LOGIN_BODY_KEY_NAME_FOR_CODE_400 = "message";
    public static final String  LOGIN_BODY_MESSAGE_FOR_CODE_400 = "Недостаточно данных для входа";

    public static final int  LOGIN_STATUS_CODE_404 = 404;
    public static final String  LOGIN_BODY_KEY_NAME_FOR_CODE_404 = "message";
    public static final String  LOGIN_BODY_MESSAGE_FOR_CODE_404 = "Учетная запись не найдена";

    //контсанты "Создать заказ"
    public static final String CREATE_ORDER_ENDPOINT = "/api/v1/orders";
    public static final int  CREATE_ORDER_STATUS_CODE_201 = 201;
    public static final String  CREATE_ORDER_BODY_KEY_NAME_FOR_CODE_201 = "track";
    public static final String FIRSTNAME = "Samanta";
    public static final String LASTNAME = "Power";
    public static final String ADDRESS = "www Leningrad";
    public static final String METRO_STATION = "Belorusskaya";
    public static final String PHONE = "937-99-92";
    public static final int RENT_TIME = new Random().nextInt(31);
    public static final String DELIVERY_DATE = String.valueOf(new Date().getDay());
    public static final String COMMENT = "Some comment";
    public static final String BLACK_COLOR = "BLACK";
    public static final String GREY_COLOR = "GREY";

    //контсанты "Список Заказов"
    public static final String  GET_ORDER_LIST_ENDPOINT = "/api/v1/orders";
    public static final int  GET_ORDER_LIST_STATUS_CODE_200 = 200;
    public static final int  GET_ORDER_LIST_STATUS_CODE_404 = 404;
    public static final String  GET_ORDER_LIST_BODY_KEY_NAME_FOR_CODE_404 = "message";
    public static final String  GET_ORDER_LIST_BODY_MESSAGE_FOR_CODE_404 = "Курьер с идентификатором {courierId} не найден";



}
