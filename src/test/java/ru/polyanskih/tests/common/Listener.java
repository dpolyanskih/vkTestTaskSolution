package ru.polyanskih.tests.common;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class Listener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult testResult) {
        Reporter.log(testResult.getThrowable().toString());
        Reporter.log("\r\nTest " + testResult.getName() + " FAILED\r\n", true);
        super.onTestFailure(testResult);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        Reporter.log("\r\nTest " + testResult.getName() + " PASSED\r\n", true);
        super.onTestFailure(testResult);
    }

    @Override
    public void onTestStart(ITestResult testResult) {
        Reporter.log("Test " + testResult.getName() + " started\r\n", true);
    }
}
