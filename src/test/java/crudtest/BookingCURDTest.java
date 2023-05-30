package crudtest;

import com.restful.booker.model.BookingDates;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BookingCURDTest extends TestBase {
    static ValidatableResponse response;

    @Test
    public void test01_GetBookingIds() {
        response = given()
                .when()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test
    public void test02_GetBooking() {
        response = given()
                .when()
                .get("/1")
                .then().log().all().statusCode(200);
    }

    @Test
    public void test03_CreateBooking() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Jin");
        bookingPojo.setLastname("Jin");
        bookingPojo.setTotalprice(111);
        bookingPojo.setDepositpaid(true);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        bookingPojo.setBookingdates(bookingDates);
        bookingPojo.setAdditionalneeds("Breakfast");

        response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingPojo)
                .post()
                .then().log().all().statusCode(200);
    }

    @Test
    public void test003() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("Sachin");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingPojo)
                .put("/5049");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test004_PartialUpdateBooking() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname("James");
        bookingPojo.setLastname("Brown");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(bookingPojo)
                .patch("/5049");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test005_DeleteBooking() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/5409");
        response.then().log().all().statusCode(200);
    }

}
