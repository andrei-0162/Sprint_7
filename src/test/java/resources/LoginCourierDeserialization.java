package resources;

import io.qameta.allure.Step;

public class LoginCourierDeserialization {
    public LoginCourierDeserialization() {
    }
@Step("Поиск и передача id курьера")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;


}
