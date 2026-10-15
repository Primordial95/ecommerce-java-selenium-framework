package com.automationexercise.tests.registration;

import com.automationexercise.base.BaseTest;
import com.automationexercise.dataproviders.UserData;
import com.automationexercise.pages.AccountConfirmationPage;
import com.automationexercise.pages.RegistrationPage;
import com.automationexercise.pages.SignupLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class RegistrationTest extends BaseTest {


    @Test(description = "TC-REG-001: Register User with multiple Data", dataProvider = "RegistrationData",
            dataProviderClass = UserData.class)
    public void registerUser(Map<String, String> registrationData){
        SignupLoginPage signupLoginPage = homePage.clickSignupLoginBtn();
        RegistrationPage registrationPage = signupLoginPage.signupUser(registrationData.get("email"), registrationData.get("name"));
        registrationPage.fillCompleteRegistrationDetails(registrationData);
        AccountConfirmationPage accountConfirmationPage=registrationPage.clickAccountCreate();
        String successMessage=accountConfirmationPage.getAccountCreationMessage();
        Assert.assertEquals(successMessage, "ACCOUNT CREATED!");
        accountConfirmationPage.clickContinueBtn();
        Assert.assertTrue(homePage.getuserLoggedIn(registrationData.get("name")),"The User is not logged in");
    }
}
