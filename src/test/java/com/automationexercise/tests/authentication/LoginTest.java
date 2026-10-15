package com.automationexercise.tests.authentication;

import com.automationexercise.base.BaseTest;
import com.automationexercise.dataproviders.UserData;
import com.automationexercise.models.Credential;
import com.automationexercise.pages.AccountConfirmationPage;
import com.automationexercise.pages.SignupLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginTest extends BaseTest {
    public static SignupLoginPage signupLoginPage;

    @Test(description = "TC-AUTH-001: Login User with correct email and password", dataProvider = "RegistrationData",
    dataProviderClass = UserData.class, priority = 1)
    public void loginWithValidCredentials(Map<String, String> loginDetails) {
        Assert.assertEquals(homePage.getHomePageLogoUrl(), "https://automationexercise.com/static/images/home/logo.png");
        signupLoginPage = homePage.clickSignupLoginBtn();
        signupLoginPage.LoginUser(loginDetails.get("email"), loginDetails.get("password"));
        Assert.assertTrue(homePage.getuserLoggedIn(loginDetails.get("name")));
        AccountConfirmationPage accountConfirmationPage = homePage.clickDeleteAccount();
        String deletionMessage = accountConfirmationPage.getAccountDeletionMessage();
        Assert.assertEquals(deletionMessage, "ACCOUNT DELETED!");
        accountConfirmationPage.clickContinueBtn();
        Assert.assertTrue(homePage.websiteNameIsPresent());
    }


    @Test(description = "TC-AUTH-001: Verify Login with invalid Credentials", dataProvider = "invalidCredentials",
            dataProviderClass = UserData.class, priority = 2)
    public void loginWithInvalidCredentials(Credential credential) {
        signupLoginPage = homePage.clickSignupLoginBtn();
        signupLoginPage.LoginUser(credential.getEmail(), credential.getPassword());
        Assert.assertTrue(signupLoginPage.getInvalidCredentialsMessage());
    }

}
