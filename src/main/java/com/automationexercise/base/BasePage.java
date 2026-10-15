package com.automationexercise.base;

import com.automationexercise.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Class for Common Page Actions.
 */
public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final int timeout = ConfigReader.getTimeout();

    protected BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    private Select getSelect(By locator){
        WebElement element = waitForElementToAppear(locator);
        return new Select(element);
    }

    protected WebElement waitForElementToBeClickable(By findBy){
        return wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }

    protected WebElement waitForElementToAppear(By findBy) {
       return wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    protected void clickElem(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected String getAttribute(By locator, String attributeName) {
        return waitForElementToAppear(locator).getAttribute(attributeName);
    }

    protected String getText(By locator){
        return waitForElementToAppear(locator).getText();
    }

    protected void sendKeys(By locator, String text){
        waitForElementToBeClickable(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected void selectByVisibleText(By locator, String value){
        getSelect(locator).selectByVisibleText(value);
    }


}
