package com.automationexercise.tests.registration;

import com.automationexercise.base.BaseTest;
import com.automationexercise.dataproviders.UserData;
import com.automationexercise.pages.RegistrationPage;
import com.automationexercise.pages.SignupLoginPage;
import org.testng.annotations.Test;

import java.util.Map;

public class RegistrationTest extends BaseTest {
    SignupLoginPage signupLoginPage;

    @Test(description = "TC-REG-001: Register User with multiple Data", dataProvider = "RegistrationData",
            dataProviderClass = UserData.class)
    public void registerUser(Map<String, String> registrationData){
        signupLoginPage = homePage.clickSignupLoginBtn();
        RegistrationPage registrationPage = signupLoginPage.signupUser(registrationData.get("name"), registrationData.get("email"));


    }
}
