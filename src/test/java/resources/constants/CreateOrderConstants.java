package resources.constants;

import java.util.Date;
import java.util.Random;

public class CreateOrderConstants {
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
}
