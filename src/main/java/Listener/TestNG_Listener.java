package Listener;

import Utilities.*;
import org.testng.*;
import org.testng.internal.IResultListener;

import java.io.File;

public class TestNG_Listener implements IInvokedMethodListener , IExecutionListener , IResultListener , ITestListener {

    File allure_results = new File("test-outputs/allure-results");
    File Logs = new File("test-outputs/logs");
    File Screenshots = new File("test-outputs/screenshots");

    //before run
    public void onExecutionStart() {
        LogsUtils.info("Text Execution Started");
        PropertiesUtils.loadProperties();
        FileUtils.deleteFiles(allure_results);
        FileUtils.cleanDirectory(Logs);
        FileUtils.cleanDirectory(Screenshots);
    }

    //after run
    public void onExecutionFinish() {
        LogsUtils.info("Text Execution Finished");
    }

    //after any method
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if(method.isTestMethod()) {

            try{
                CustomSoftAssert.customSoftAssertAll();
            } catch (AssertionError e) {
                testResult.setStatus(ITestResult.FAILURE);
                testResult.setThrowable(e);
            }

            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS -> ScreenShotUtils.takeScreenShot("passed - " + testResult.getName());
                case ITestResult.FAILURE -> ScreenShotUtils.takeScreenShot("failed - " + testResult.getName());
                case ITestResult.SKIP -> ScreenShotUtils.takeScreenShot("skipped - " + testResult.getName());
            }

            AllureUtils.attachLogToAllureReport();

        }
    }

    //on test case success
    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("Test Case Passed : " + result.getName());
    }

    //on test case failure
    public void onTestFailure(ITestResult result) {
        LogsUtils.error("Test Case Failed : " + result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        LogsUtils.warn("Test Case Skipped : " + result.getName());
    }


}
