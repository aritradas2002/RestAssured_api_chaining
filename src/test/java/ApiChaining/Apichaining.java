package ApiChaining;

import Pojo.Bookingdates_pojo;
import Pojo.Create_booking_pojo;
import Pojo.Login_Pojo;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class Apichaining {
    Logger logger =
            LogManager.getLogger(this.getClass());
    String token;
    int id;


    Login_Pojo login_pojo;
    Create_booking_pojo updateBookingPojo;
    @BeforeTest
    public void setup(){
        RestAssured.filters(new AllureRestAssured());
        logger.info("Set the base Url");
        login_pojo=new Login_Pojo();


        RestAssured.baseURI="https://restful-booker.herokuapp.com";

    }
    @Epic("Authentication Module")
    @Feature("Login API")
    @Story("Verify user authentication with valid credentials")
    @Description("This test verifies that the user can successfully login using valid username and password and receive authentication token.")
    @Test(priority = 1)
    public void Login(){
        login_pojo.setUsername("admin");
        login_pojo.setPassword("password123");
        Response response= given()
                .header("Content-Type", "application/json")
                .body(login_pojo)
                .when()
                .post("/auth")
                .then()
                .statusCode(greaterThanOrEqualTo(200))
                .log().all()
                .extract().response();
        logger.info("Login Successful");
        logger.info("status code"+ response.getStatusCode());
        logger.info("Extract the token from response");
        token=response.jsonPath().getString("token");
        System.out.println("token"+token);

    }
    @Epic("Booking Module")
    @Feature("Get Booking IDs API")
    @Story("Verify retrieval of all booking IDs")
    @Description("This test verifies that all booking IDs are fetched successfully from the booking service.")
    @Test(priority = 2)
    public void getbookingids(){
        Response response= given()
                .header("Content-Type", "application/json")
                .when()
                .get("/booking")
                .then()
                .statusCode(greaterThanOrEqualTo(200))
                .log().all()
                .extract().response();
        logger.info("status code"+ response.getStatusCode());
        logger.info("Take random id");
        List<Integer>ids=response.jsonPath().getList("bookingid");
        Random random=new Random();
        id=ids.get(random.nextInt(ids.size()));
        logger.info("booking id is"+id);

    }
    @Epic("Booking Module")
    @Feature("Get Booking Details API")
    @Story("Verify retrieval of booking details by booking ID")
    @Description("This test verifies that booking details are retrieved successfully using a valid booking ID.")
    @Test(priority = 3)
    public void getbooking(){
        Response response= given()
                .header("Content-Type", "application/json")
                .when()
                .get("/booking/"+id)
                .then()
                .statusCode(greaterThanOrEqualTo(200))
                .log().all()
                .extract().response();
        logger.info("status code"+ response.getStatusCode());
    }
    @Epic("Booking Management Module")
    @Feature("Create Booking API")
    @Story("Verify creation of new booking")
    @Description("This test verifies that a new booking can be created successfully with valid booking payload.")
    @Test(priority = 4)
    public void Create_booking() {
        Bookingdates_pojo bookingDates =
                new Bookingdates_pojo(
                        "2018-01-01",
                        "2019-01-01"
                );



        // Main request object
       Create_booking_pojo bookingRequest =
                new Create_booking_pojo(
                        "Jim",
                        "Brown",
                        111,
                        true,
                        bookingDates,
                        "Breakfast"
                );



        // API Request
       Response response= given()

                .header("Content-Type","application/json")


                .body(bookingRequest)

                .when()

                .post("/booking")

                .then()

                .statusCode(greaterThanOrEqualTo(200))

                .log().all().extract().response();
        logger.info("status code"+ response.getStatusCode());
    }
    @Epic("Booking Management Module")
    @Feature("Update Booking API")
    @Story("Verify update of existing booking")
    @Description("This test verifies that an existing booking is updated successfully using valid booking ID and updated payload.")
    @Test(priority = 5)
    public void Update_booking() {
        Bookingdates_pojo bookingDates =
                new Bookingdates_pojo(
                        "2027-01-01",
                        "2027-01-01"
                );



        // Main request object
        Create_booking_pojo bookingRequest =
                new Create_booking_pojo(
                        "Rajat",
                        "Brown",
                        113,
                        true,
                        bookingDates,
                        "Breakfast"
                );



        // API Request
       Response response= given()

                .header("Content-Type","application/json")
                .header("Cookie","token=" + token)

                .body(bookingRequest)

                .when()

                .put("/booking/"+id)

                .then()

                .statusCode(greaterThanOrEqualTo(200))

                .log().all().extract().response();
        logger.info("status code"+ response.getStatusCode());

    }
    @Epic("Booking Management Module")
    @Feature("Delete Booking API")
    @Story("Verify deletion of booking")
    @Description("This test verifies that a booking is deleted successfully using a valid booking ID.")
    @Test(priority = 6)
    public void delete_booking(){
       Response response=given() .header("Content-Type","application/json")
                    .header("Cookie","token=" + token)

                .when()

                .delete("/booking/"+id)

                .then()

                .statusCode(greaterThanOrEqualTo(200))

                .log().all().extract().response();
        logger.info("status code"+ response.getStatusCode());
    }

}
