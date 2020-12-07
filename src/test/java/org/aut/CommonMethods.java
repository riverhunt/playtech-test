package org.aut;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Reporter;

import java.io.File;
import java.io.InputStream;

public class CommonMethods {

    public static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);

        options.addArguments("--window-size=1200,1000");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--test-type");
        options.addArguments("--ignore-certificate-errors");
        options.setCapability("applicationCacheEnabled", false);

        return new ChromeDriver(options);
    }

    public static TestData loadTestData() {
        try (
                InputStream inputStream = TestData.class.getClassLoader().getResourceAsStream("testdata.json")
        ) {
            return new ObjectMapper().readValue(inputStream, TestData.class);
        } catch (Exception e) {
            Reporter.log("Failed to load test data." + "<br/>");
            System.err.println("Failed to load test data.");
            return null;
        }
    }

    public static void takeScreenshot(WebDriver driver) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotName = new File(System.getProperty("user.dir")
                    + File.separator + "test-output"
                    + File.separator + "Screenshots"
                    + File.separator + System.currentTimeMillis() + "_" + ".png");
            FileUtils.copyFile(screenshot, screenshotName);
            Reporter.log("<br><img src='" + screenshotName + "' width='800'/><br>");
        } catch (Exception e) {
            Reporter.log("Failed to take screenshot" + "<br/>");
            System.err.println("Failed to take screenshot");
        }
    }
}
