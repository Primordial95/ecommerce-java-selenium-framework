package com.automationexercise.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupLoginPage {
    WebDriver driver;

    public SignupLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text()='Login to your account']/parent::div//form//input[@type='email']")
    WebElement loginEmailTxtBox;

    @FindBy(xpath = "//h2[text()='Login to your account']/parent::div//form//input[@type='password']")
    WebElement loginPasswordTxtBox;

    @FindBy(xpath = "//h2[text()='Login to your account']/parent::div//form//button[@type='submit']")
    WebElement loginBtn;

    @FindBy(xpath = "//h2[text()='New User Signup!']/parent::div//form//input[@type='email']")
    WebElement signupEmailTxtBox;

    @FindBy(xpath = "//h2[text()='New User Signup!']/parent::div//form//input[@type='text']")
    WebElement signupNameTxtBox;

    @FindBy(xpath = "//h2[text()='New User Signup!']/parent::div//form//button[@type='submit']")
    WebElement signupBtn;


    public RegistrationPage signupUser(String username, String password){
        signupEmailTxtBox.sendKeys(username);
        signupNameTxtBox.sendKeys(password);
        signupBtn.click();
        return new RegistrationPage(driver);
    }

    public void LoginUser(String username, String password){
        loginEmailTxtBox.sendKeys(username);
        loginPasswordTxtBox.sendKeys(password);
        loginBtn.click();
    }

}
