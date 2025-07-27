package org.apitestinglearning.tests.E2E_Intergration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.apitestinglearning.Base.BaseTest;
import org.apitestinglearning.EndPoints.APIConstraints.API_Constraints;
import org.apitestinglearning.POJO.Booking;
import org.apitestinglearning.POJO.Booking_Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class E2EndFlow_API extends BaseTest {

    // TestE2EFlow_01

    //  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request


    @Test(groups = "qa", priority = 1)
    @Owner("Parveen Chaudhary")
    @Description("TC_01 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext) {

        requestSpecification.basePath(API_Constraints.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.CreatePayloadWith_ValidDataString())
                .post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Booking_Response bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertAction.Verify_StringKey(bookingResponse.getBooking().getFirstname(), "Parveen");
        assertAction.verifyStringKeyNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }


    @Test(groups = "qa", priority = 2)
    @Owner("Parveen Chaudhary")
    @Description("TC_01 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGET = API_Constraints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertAction.verifyStringKeyNotNull(booking.getFirstname());


    }


    @Test(groups = "qa", priority = 3)
    @Owner("Parveen Chaudhary")
    @Description("TC_01 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext iTestContext) {


        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        System.out.println(token);

        iTestContext.setAttribute("token", token);


        String basePathPUTPATCH = API_Constraints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();


        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertAction.verifyStringKeyNotNull(booking.getFirstname());
        assertAction.Verify_StringKey(booking.getFirstname(), "Pintu");


    }

    @Test(groups = "qa", priority = 4)
    @Owner("Parveen Chaudhary")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");

        String basePathDELETE = API_Constraints.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);


    }

}