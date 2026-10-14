package com.automationexercise.tests.authentication;

import com.automationexercise.base.BaseTest;
import com.automationexercise.dataproviders.RegistrationData;
import com.automationexercise.pages.SignupLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    SignupLoginPage signupLoginPage;

    @Test(description = "TC-AUTH-001: Login User with correct email and password", dataProvider = "getValidCredentials",
            dataProviderClass = RegistrationData.class)
    public void loginWithCorrectCredentials(String username, String password) {
        Assert.assertEquals(homePage.getHomePageLogoUrl(), "https://automationexercise.com/static/images/home/logo.png");
        signupLoginPage=homePage.clickSignupLoginBtn();
        signupLoginPage.LoginUser(username, password);
        Assert.assertTrue(homePage.userLoggedIn("helloworld"));
        String deletionMessage = homePage.deleteAccount();
        Assert.assertEquals(deletionMessage, "ACCOUNT DELETED!");
        Assert.assertTrue(homePage.websiteNameIsPresent());
    }
}
