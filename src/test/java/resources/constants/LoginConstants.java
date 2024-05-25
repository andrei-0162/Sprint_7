package resources.constants;

public class LoginConstants {
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
}
