package com.automationexercise.pages;

import com.automationexercise.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountConfirmationPage extends BasePage {
    private static final By ACCOUNT_DELETE_MESSAGE = By.xpath("//h2[@data-qa='account-deleted']//b");
    private static final By ACCOUNT_CREATED_MESSAGE = By.xpath("//h2[@data-qa='account-created']//b");
    private static final By CONTINUE_BTN = By.xpath("//a[text()='Continue']");

    public AccountConfirmationPage(WebDriver driver){
        super(driver);
    }

    public String getAccountCreationMessage() {
        return getText(ACCOUNT_CREATED_MESSAGE);
    }

    public String getAccountDeletionMessage() {
        return getText(ACCOUNT_DELETE_MESSAGE);
    }

    public HomePage clickContinueBtn(){
        clickElem(CONTINUE_BTN);
        return new HomePage(driver);
    }




}
