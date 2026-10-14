package com.automationexercise.tests.authentication;

import com.automationexercise.dataproviders.RegistrationData;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.SignupLoginPage;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginTest {
    WebDriver driver;
    HomePage homePage;
    SignupLoginPage signupLoginPage;
    public LoginTest(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(
                "prefs",
                Map.of(
                        "profile.default_content_setting_values.notifications", 2
                )
        );
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        this.driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
    }

    @Test(description = "TC-AUTH-001: Login User with correct email and password", dataProvider = "getValidCredentials",
            dataProviderClass = RegistrationData.class)
    public void loginWithCorrectCredentials(String username, String password) {
        driver.get("https://automationexercise.com/");
        Assert.assertEquals(homePage.getHomePageLogoUrl(), "https://automationexercise.com/static/images/home/logo.png");
        signupLoginPage=homePage.clickSignupLoginBtn();
        signupLoginPage.LoginUser(username, password);
        Assert.assertTrue(homePage.userLoggedIn("helloworld"));
        driver.quit();
    }
}
