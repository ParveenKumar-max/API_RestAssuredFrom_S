package org.apitestinglearning.tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.apitestinglearning.Base.BaseTest;
import org.apitestinglearning.EndPoints.APIConstraints.API_Constraints;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {

    // Token creation we need for PUT, PATCH and DELETE.


    @Test(groups = "reg", priority = 1)
    @Owner("Parveen Chaudhary")
    @Description("TC_04 -- Create token with valid data")
    public void testCreateToken_POST(){

        // Prep of Req
        requestSpecification.basePath(API_Constraints.AUTH_URL);

        // Making of the Request.
        response = RestAssured.given(requestSpecification)
                .when()
                .body(payloadManager.CreatePayload_setValidToken())
                .post();
       System.out.println(response.asString());

        // Extraction ( JSON String response to Java Object

        String token = payloadManager.getTokenFromJson(response.asString());
        System.out.println("Token created" + " " + token);

        // Validation of the request.
        assertAction.verifyStringKeyNotNull(token);
    }






}
