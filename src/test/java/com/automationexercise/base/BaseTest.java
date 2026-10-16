package com.automationexercise.base;

import com.automationexercise.config.ConfigReader;
import com.automationexercise.pages.HomePage;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BaseTest {

    public WebDriver initializeDriver(){
        WebDriver driver;
        String browser_name = ConfigReader.getBrowserName();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless"));
        if (browser_name.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", Map.of(
                    "profile.default_content_setting_values.notifications", 2,
                    "profile.default_content_setting_values.ads", 2,
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false,
                    "profile.password_manager_leak_detection", false,
                    "safebrowsing.enabled", false,
                    "autofill.profile_enabled", false,
                    "autofill.credit_card_enabled", false
            ));

            options.addArguments("--incognito");
            options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
            options.addArguments("--disable-notifications");
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            if(headless)
                options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
            blockAds(driver);
        } else if (browser_name.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser name" + browser_name);
        }
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);

        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        initializeDriver();
        getDriver().get(ConfigReader.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver currentDriver = getDriver();
        try {
            if (currentDriver != null)
                currentDriver.quit();
        }
        finally {
            DriverManager.unload();
        }
    }

    protected HomePage getHomePage(){
        return new HomePage(getDriver());
    }

    public WebDriver getDriver(){
        return DriverManager.getDriver();
    }

    private void blockAds(WebDriver webDriver) {
        if (webDriver instanceof ChromiumDriver chromium) {
            chromium.executeCdpCommand("Network.enable", Map.of());
            chromium.executeCdpCommand("Network.setBlockedURLs", Map.of("urls", List.of(
                    "*googlesyndication.com*",
                    "*doubleclick.net*",
                    "*googleadservices.com*",
                    "*adservice.google.*",
                    "*pagead2*",
                    "*googletagservices.com*"
            )));
        }
    }
}
