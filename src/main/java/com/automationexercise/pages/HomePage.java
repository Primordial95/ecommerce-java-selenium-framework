package com.automationexercise.pages;

import com.automationexercise.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By SIGNUP_LOGIN_BTN = By.xpath("//a[normalize-space(.)='Signup / Login']");
    private static final By HOME_PAGE_LOGO = By.xpath("//img[@alt='Website for automation practice']");
    private static final By DELETE_ACCOUNT_BTN = By.xpath("//a[normalize-space(.)='Delete Account']");
    private static final By WEBSITE_NAME = By.xpath("(//h1[text()='Exercise' and //span[normalize-space(.)='Automation']])[1]");


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

    public boolean getuserLoggedIn(String username) {
        log.info("Verifying if User logged in or not {}", username);
        String loggedInMessageString = "//a[normalize-space(.) = 'Logged in as %s']";
        By loggedInMessage = By.xpath(String.format(loggedInMessageString, username));
        try {
            waitForElementToAppear(loggedInMessage);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public AccountConfirmationPage clickDeleteAccount() {
        clickElem(DELETE_ACCOUNT_BTN);
        return new AccountConfirmationPage(driver);
    }

    public boolean websiteNameIsPresent() {
        log.info("Verifying Website Name is displayed");
        return waitForElementToAppear(WEBSITE_NAME).isDisplayed();
    }


}
