package com.automationexercise.tests.authentication;

import com.automationexercise.base.BaseTest;
import com.automationexercise.dataproviders.UserData;
import com.automationexercise.models.Credential;
import com.automationexercise.pages.SignupLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    public static SignupLoginPage signupLoginPage;

    @Test(description = "TC-AUTH-001: Login User with correct email and password")
    public void loginWithValidCredentials(String username, String password) {
        Assert.assertEquals(homePage.getHomePageLogoUrl(), "https://automationexercise.com/static/images/home/logo.png");
        signupLoginPage = homePage.clickSignupLoginBtn();
        signupLoginPage.LoginUser(username, password);
        Assert.assertTrue(homePage.userLoggedIn("helloworld"));
        String deletionMessage = homePage.deleteAccount();
        Assert.assertEquals(deletionMessage, "ACCOUNT DELETED!");
        Assert.assertTrue(homePage.websiteNameIsPresent());
    }


    @Test(description = "TC-AUTH-001: Verify Login with invalid Credentials", dataProvider = "invalidCredentials",
            dataProviderClass = UserData.class)
    public void loginWithInvalidCredentials(Credential credential) {
        signupLoginPage = homePage.clickSignupLoginBtn();
        signupLoginPage.LoginUser(credential.getEmail(), credential.getPassword());
        Assert.assertTrue(signupLoginPage.getInvalidCredentialsMessage());
    }

}
