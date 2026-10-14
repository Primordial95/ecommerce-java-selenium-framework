package com.automationexercise.pages;

import com.automationexercise.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupLoginPage extends BasePage {

    public SignupLoginPage(WebDriver driver) {
        super(driver);
}

    private final By loginEmailTxtBox = By.xpath("//h2[text()='Login to your account']/parent::div//form//input[@type='email']");
    private final By loginPasswordTxtBox = By.xpath("//h2[text()='Login to your account']/parent::div//form//input[@type='password']");
    private final By loginBtn = By.xpath("//h2[text()='Login to your account']/parent::div//form//button[@type='submit']");
    private final By signupEmailTxtBox = By.xpath("//h2[text()='New User Signup!']/parent::div//form//input[@type='email']");
    private final By signupNameTxtBox = By.xpath("//h2[text()='New User Signup!']/parent::div//form//input[@type='text']");
    private final By signupBtn = By.xpath("//h2[text()='New User Signup!']/parent::div//form//button[@type='submit']");
    private final By invalidCredentialMessage = By.xpath("//p[text()='Your email or password is incorrect!']");


    public RegistrationPage signupUser(String username, String password) {
        sendKeys(signupEmailTxtBox, username);
        sendKeys(signupNameTxtBox, username);
        clickElem(signupBtn);
        return new RegistrationPage(driver);
    }

    public void LoginUser(String username, String password) {
        sendKeys(loginEmailTxtBox, username);
        sendKeys(loginPasswordTxtBox, username);
        clickElem(loginBtn);
    }
    public boolean getInvalidCredentialsMessage(){
        return waitForElementToAppear(invalidCredentialMessage).isDisplayed();
    }

}
