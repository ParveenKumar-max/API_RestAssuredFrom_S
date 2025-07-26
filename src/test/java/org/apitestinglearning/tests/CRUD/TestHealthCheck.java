package org.apitestinglearning.tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.apitestinglearning.Base.BaseTest;
import org.apitestinglearning.EndPoints.APIConstraints.API_Constraints;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {

 @Test(groups = "reg", priority = 1)
 @Owner("Parveen Chaudhary")
 @Description("TC_05 -- Verify Health Check API with 200 status code")
 public void testHealthCheck_API(){

     requestSpecification.basePath(API_Constraints.PING_URL);

     response = RestAssured.given(requestSpecification).when().get();

     validatableResponse = response.then().log().all();
     validatableResponse.statusCode(201);

 }

}
