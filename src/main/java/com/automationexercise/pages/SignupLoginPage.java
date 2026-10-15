package com.automationexercise.pages;

import com.automationexercise.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage extends BasePage {

    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    private static final By LOGIN_EMAIL_FIELD = By.xpath("//h2[text()='Login to your account']/parent::div//form//input[@type='email']");
    private static final By LOGIN_PASSWORD_FIELD = By.xpath("//h2[text()='Login to your account']/parent::div//form//input[@type='password']");
    private static final By LOGIN_BTN = By.xpath("//h2[text()='Login to your account']/parent::div//form//button[@type='submit']");
    private static final By SIGNUP_EMAIL_FIELD = By.xpath("//h2[text()='New User Signup!']/parent::div//form//input[@type='email']");
    private static final By SIGNUP_NAME_FIELD = By.xpath("//h2[text()='New User Signup!']/parent::div//form//input[@type='text']");
    private static final By SIGNUP_BTN = By.xpath("//button[@data-qa='signup-button']");
    private static final By INVALID_CREDENTIAL_MESSAGE = By.xpath("//p[text()='Your email or password is incorrect!']");


    public RegistrationPage signupUser(String email, String password) {
        sendKeys(SIGNUP_EMAIL_FIELD, email);
        sendKeys(SIGNUP_NAME_FIELD, password);
        clickElem(SIGNUP_BTN);
        return new RegistrationPage(driver);
    }

    public void LoginUser(String email, String password) {
        sendKeys(LOGIN_EMAIL_FIELD, email);
        sendKeys(LOGIN_PASSWORD_FIELD, password);
        clickElem(LOGIN_BTN);
    }

    public boolean getInvalidCredentialsMessage() {
        return waitForElementToAppear(INVALID_CREDENTIAL_MESSAGE).isDisplayed();
    }

}
