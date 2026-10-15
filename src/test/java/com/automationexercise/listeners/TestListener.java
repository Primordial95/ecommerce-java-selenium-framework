package com.automationexercise.listeners;

import com.automationexercise.base.BaseTest;
import com.automationexercise.reports.ExtentManager;
import com.automationexercise.reports.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result){
        String name = result.getMethod().getDescription();
        if (name == null || name.isBlank()) {
            name = result.getMethod().getMethodName();
        }
        ExtentTest test = ExtentManager.getInstance().createTest(name);
        test.assignCategory(result.getTestClass().getRealClass().getSimpleName());
        ExtentTestManager.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result){
        ExtentTestManager.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        ExtentTest test = ExtentTestManager.get();
        test.fail(result.getThrowable());

        if (result.getInstance() instanceof BaseTest base) {
            WebDriver driver = base.getDriver();
            if (driver instanceof TakesScreenshot screenshotDriver) {
                try {
                    String base64 = screenshotDriver.getScreenshotAs(OutputType.BASE64);
                    test.fail("Failure screenshot",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
                } catch (Exception e) {
                    test.warning("Could not capture screenshot: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result){
        ExtentTestManager.get().skip(
                result.getThrowable() != null ? result.getThrowable().getMessage() : "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context){
        ExtentManager.getInstance().flush();   // writes the HTML file
        ExtentTestManager.remove();
    }
}
