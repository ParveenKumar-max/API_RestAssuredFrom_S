package org.apitestinglearning.Asserts;

import io.restassured.response.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.testng.Assert.assertEquals;

public class AssertAction {

    // Assert Action, which scenario's you want to TEST or VERIFY.

    public void Verify_ResponseBody(String actual, String expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void Verify_ResponseBody(int actual, int expected, String description) {
        assertEquals(actual, expected, description);
    }

    // These above two method are the example of Method overloading,
    // Same method name but different parameters

    public void Verify_StatusCode(Response response, Integer expected) {
        assertEquals(response.getStatusCode(), expected);
    }

    public void Verify_StatusKey(String KeyExpected, String KeyActual) {
        // assertJ library function we are using below

        assertThat(KeyExpected).isNotNull();
        assertThat(KeyExpected).isNotBlank();
        assertThat(KeyExpected).isEqualTo(KeyActual);

    }

    public void verifyStringKeyNotNull(Integer keyExpect) {
        // AssertJ
        assertThat(keyExpect).isNotNull();
    }

    public void verifyStringKeyNotNull(String keyExpect) {
        // AssertJ
        assertThat(keyExpect).isNotNull();
    }


}
