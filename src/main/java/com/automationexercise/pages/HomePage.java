package com.automationexercise.pages;

import com.automationexercise.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By SIGNUP_LOGIN_BTN = By.xpath("//a[normalize-space(.)='Signup / Login']");
    private static final By HOME_PAGE_LOGO = By.xpath("//img[@alt='Website for automation practice']");
    private static final By DELETE_ACCOUNT_BTN = By.xpath("//a[normalize-space(.)='Delete Account']");
    private static final By ACCOUNT_DELETE_MESSAGE = By.xpath("//h2[@data-qa='account-deleted']//b");
    private static final By CONTINUE_BTN = By.xpath("//a[text()='Continue']");
    private static final By WEBSITE_NAME = By.xpath("(//h1[text()='Exercise' and //span[normalize-space(.)='Automation']])[1]");
    private static final By ACCOUNT_CREATED_MESSAGE = By.xpath("//h2[@data-qa='account-created']//b");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SignupLoginPage clickSignupLoginBtn() {
        clickElem(SIGNUP_LOGIN_BTN);
        return new SignupLoginPage(driver);
    }

    public String getHomePageLogoUrl() {
        return getAttribute(HOME_PAGE_LOGO, "src");
    }

    public boolean userLoggedIn(String username) {
        String loggedInMessageString = "//a[normalize-space(.) = 'Logged in as %s']";
        By loggedInMessage = By.xpath(String.format(loggedInMessageString, username));
        try {
            waitForElementToAppear(loggedInMessage);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String deleteAccount() {
        clickElem(DELETE_ACCOUNT_BTN);
        String message = getText(ACCOUNT_DELETE_MESSAGE);
        clickElem(CONTINUE_BTN);
        return message;
    }

    public boolean websiteNameIsPresent() {
        return waitForElementToAppear(WEBSITE_NAME).isDisplayed();
    }

    public String getAccountCreationMessage() {
        return getText(ACCOUNT_CREATED_MESSAGE);
    }

}
