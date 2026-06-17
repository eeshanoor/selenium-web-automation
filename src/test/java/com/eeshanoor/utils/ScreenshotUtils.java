package com.eeshanoor.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

/**
 * Screenshot capture utility with Allure attachment support.
 */
public class ScreenshotUtils {

    public static void captureOnFailure(WebDriver driver, ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureAndAttach(driver, result.getName());
        }
    }

    public static void captureAndAttach(WebDriver driver, String name) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }
}