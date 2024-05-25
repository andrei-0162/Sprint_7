package resources;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import resources.constants.CreateDeleteCourierConstants;

import static io.restassured.RestAssured.given;

public class DeleteCourier {

    private int id;
    public DeleteCourier() {
    }

    public DeleteCourier(int id) {
        this.id = id;
    }
@Step("Удаление курьера")
    public Response deleteCourier (int id) {
//        System.out.println("Курьер удален: id-" + id);
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete(CreateDeleteCourierConstants.DELETE_COURIER_ENDPOINT + id);

    }


    public void deleteCourier (CreateCourier newCreateCourier) {

        String login = newCreateCourier.getLogin();
        String password = newCreateCourier.getPassword();

        LoginCourier newLogin = new LoginCourier(login, password);
        Response loginResponse = newLogin.loginOfCourierPOST(newLogin);
        LoginCourierDeserialization loggedCourier = loginResponse.as(LoginCourierDeserialization.class);
        //Удаление курьера
        deleteCourier(loggedCourier.getId());
    }

}
