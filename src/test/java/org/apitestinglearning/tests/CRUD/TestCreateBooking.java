package org.apitestinglearning.tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.apitestinglearning.Base.BaseTest;
import org.apitestinglearning.EndPoints.APIConstraints.API_Constraints;
import org.apitestinglearning.POJO.Booking_Response;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {


    @Test(groups = "reg", priority = 1)
    @Owner("Parveen")
    @Description("TC_01_Verify that Booking is created with Valid data")
    public void testCreateBooking_positive(){

        // Setup and Making a Request.
        requestSpecification.basePath(API_Constraints.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when()
                .body(payloadManager.CreatePayloadWith_ValidDataString())
                .log().all().post();
        System.out.println(response.asString());

        //Extraction
        Booking_Response bookingResponse = payloadManager.bookingResponseJava(response.asString());

        //Verification Pat

        assertAction.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertAction.Verify_StringKey(bookingResponse.getBooking().getFirstname(),"Parveen");

    }

    @Test(groups = "reg", priority = 2)
    @Owner("Parveen")
    @Description("TC_02_Verify that Booking is created with InValid data")
    public void testCreateBooking__negative(){

        requestSpecification.basePath(API_Constraints.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when()
                .body("{}").log().all().post();

        System.out.println(response.asString());

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);
    }


    @Test(groups = "reg", priority = 2)
    @Owner("Parveen")
    @Description("TC_03_Verify that Booking isn't created with missing data")
    public void testCreateBooking__missingData(){

        requestSpecification.basePath(API_Constraints.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when()
                .body(payloadManager.CreatePayloadWith_ValidMissingDataString()).log().all().post();

        System.out.println(response.asString());

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);
    }

}
