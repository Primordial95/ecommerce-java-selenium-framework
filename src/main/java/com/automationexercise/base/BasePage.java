package com.automationexercise.base;

import com.automationexercise.config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    protected final Logger log = LogManager.getLogger(getClass());

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
        log.info("Clicking {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected String getAttribute(By locator, String attributeName) {
        log.info("Getting Attribute {} :: {}",locator,attributeName);
        return waitForElementToAppear(locator).getAttribute(attributeName);
    }

    protected String getText(By locator){
        String text = waitForElementToAppear(locator).getText();
        log.info("Read Text {} from {}",text, locator);
        return text;
    }

    protected void sendKeys(By locator, String text){
        log.info("Enter into {} with {}",locator,text);
        waitForElementToBeClickable(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected void selectByVisibleText(By locator, String value){
        log.info("Selecting {} by visible text {}", locator, value);
        getSelect(locator).selectByVisibleText(value);
    }


}
