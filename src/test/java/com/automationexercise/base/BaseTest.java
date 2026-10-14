package com.automationexercise.base;

import com.automationexercise.config.ConfigReader;
import com.automationexercise.pages.HomePage;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;

    public WebDriver initializeDriver() throws IOException {
        String browser_name = ConfigReader.getBrowserName();
        if (browser_name.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", Map.of(
                    "profile.default_content_setting_values.notifications", 2,
                    // Disable Chrome password manager prompts
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false,
                    // Disable password leak detection warning
                    "profile.password_manager_leak_detection", false,
                    // Block some intrusive ads
                    "profile.default_content_setting_values.ads", 2
            ));
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(options);
        } else if (browser_name.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser name" + browser_name);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        driver.get(ConfigReader.getBaseUrl());
        homePage = new HomePage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

}
