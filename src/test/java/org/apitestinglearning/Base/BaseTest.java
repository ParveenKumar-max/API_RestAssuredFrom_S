package org.apitestinglearning.Base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apitestinglearning.Asserts.AssertAction;
import org.apitestinglearning.EndPoints.APIConstraints.API_Constraints;
import org.apitestinglearning.Module.PayloadManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    // Base Test file is common for Everyone.
    // This file we define test case that are common to all TC's


    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public AssertAction assertAction;

    // Call all the methods in base Test file.

    @BeforeTest
    public void setUp() {
        System.out.println("Starting of the Project or Test");
        payloadManager = new PayloadManager();
        assertAction = new AssertAction();

        // In the above setUp() method, we create an instance of class,
        // so that we can use their functions

    /*    requestSpecification = RestAssured.given();
        requestSpecification.baseUri(API_Constraints.BASE_URL);
        requestSpecification.contentType(ContentType.JSON).log().all();    */

        // There is one more way to define or call the baseURl -->  RequestSpecBuilder

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(API_Constraints.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();


    }

    @AfterTest
    public void tearDown() {
        System.out.println("Ending of the program or Test");
    }

}




