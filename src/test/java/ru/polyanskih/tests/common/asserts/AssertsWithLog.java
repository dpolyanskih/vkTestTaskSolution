package ru.polyanskih.tests.common.helpers;

import com.vk.api.sdk.exceptions.ApiException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Reporter.log;

public class AssertWithLog {

    public static void assertEqualsWithLog(Object actual, Object expected, String paramName, String message) {
        assertEquals(actual, expected, message);
        log("Passed check for '" + paramName + "', value is '" + expected + "'", true);
    }

    public static void assertNotNullWithLog(Object object, String paramName, String message) {
        assertNotNull(object, message);
        log("Passed check for '" + paramName + "', field is present in response");
    }

    public static void compareException(ApiException actualException, ApiException expectedException) {
        assertEqualsWithLog(actualException.getCode(), expectedException.getCode(), "exception code", "Incorrect exception code");
        assertEqualsWithLog(actualException.getDescription(), expectedException.getDescription(), "exception description", "Incorrect exception description");
        assertEqualsWithLog(actualException.getMessage(), expectedException.getMessage(), "exception message", "Incorrect exception message");
    }
}
