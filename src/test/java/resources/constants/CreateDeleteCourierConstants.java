package resources.constants;

public class CreateDeleteCourierConstants {
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
}
