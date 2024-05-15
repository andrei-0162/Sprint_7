package resources;

import io.qameta.allure.Step;

public class LoginCourierDeserealization {
    public LoginCourierDeserealization() {
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
