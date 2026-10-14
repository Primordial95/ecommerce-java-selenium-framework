package com.automationexercise.pages;

import com.automationexercise.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By signupLoginBtn = By.xpath("//a[normalize-space(.)='Signup / Login']");
    private final By homePageLogo = By.xpath("//img[@alt='Website for automation practice']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SignupLoginPage clickSignupLoginBtn() {
        clickElem(signupLoginBtn);
        return new SignupLoginPage(driver);
    }

    public String getHomePageLogoUrl() {
        return getAttribute(homePageLogo, "src");
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

}
