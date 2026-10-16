package com.automationexercise.pages;

import com.automationexercise.base.BasePage;
import com.automationexercise.utils.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class RegistrationPage extends BasePage {

    private static final String TITLE_RADIO_BTN_XPATH = "//label[normalize-space(.)='%s']";
    private static final By NAME_FIELD = By.id("name");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By DOB_DAY_DROPDOWN = By.id("days");
    private static final By DOB_MONTH_DROPDOWN = By.id("months");
    private static final By DOB_YEAR_DROPDOWN = By.id("years");
    private static final By NEWSLETTER_LABEL = By.xpath("//div[@id='uniform-newsletter']/following-sibling::label");
    private static final By SPECIAL_OFFERS_LABEL = By.xpath("//div[@id='uniform-optin']/following-sibling::label");
    private static final By FIRST_NAME_FIELD = By.id("first_name");
    private static final By LAST_NAME_FIELD = By.id("last_name");
    private static final By COMPANY_FIELD = By.id("company");
    private static final By ADDRESS_FIELD = By.id("address1");
    private static final By COUNTRY_DROPDOWN = By.id("country");
    private static final By STATE_FIELD = By.id("state");
    private static final By CITY_FIELD = By.id("city");
    private static final By ZIPCODE_FIELD = By.id("zipcode");
    private static final By MOBILE_FIELD = By.id("mobile_number");
    private static final By CREATE_ACCOUNT_BTN =
            By.xpath("//button[@type='submit' and @data-qa='create-account']");
    private static final By ACCOUNT_CREATED_CONFIRMATION = By.xpath("//h2[@data-qa='account-created']//b");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private static By titleRadioBtn(String title) {
        return By.xpath(String.format(TITLE_RADIO_BTN_XPATH, title));
    }

    public void selectTitle(Map<String, String> data){
        log.info("Selecting Title {}", data.get("title"));
        clickElem(titleRadioBtn(data.get("title")));
    }

    public void fillAccountInformation(Map<String, String> data){
        log.info("Enter name: {}", data.get("name"));
        sendKeys(NAME_FIELD, data.get("name"));
        log.info("Enter password: {}", data.get("password"));
        sendKeys(PASSWORD_FIELD, data.get("password"));
    }

    public void selectDateOfBirth(Map<String, String> data){
        log.info("Selecting Date of Birth: {}", data.get("dateofbirth"));
        String dateofbirth =DateUtils.parsedDate(data.get("dateofbirth"));
        String[] dob = dateofbirth.split("-");
        selectByVisibleText(DOB_DAY_DROPDOWN, String.valueOf(Integer.parseInt(dob[0])));
        selectByVisibleText(DOB_MONTH_DROPDOWN, dob[1]);
        selectByVisibleText(DOB_YEAR_DROPDOWN, dob[2]);
    }

    public void selectCheckBoxNewsletter(){
        log.info("Checking News letter and promotions");
        clickElem(NEWSLETTER_LABEL);
        clickElem(SPECIAL_OFFERS_LABEL);
    }

    public void filAddressInformation(Map<String, String> data){
        log.info("Entering the Whole address information");
        sendKeys(FIRST_NAME_FIELD, data.get("firstname"));
        sendKeys(LAST_NAME_FIELD, data.get("lastname"));
        sendKeys(COMPANY_FIELD, data.get("company"));
        sendKeys(ADDRESS_FIELD, data.get("address"));
        selectByVisibleText(COUNTRY_DROPDOWN, data.get("country"));
        sendKeys(STATE_FIELD, data.get("state"));
        sendKeys(CITY_FIELD, data.get("city"));
        sendKeys(ZIPCODE_FIELD, data.get("zipcode"));
        sendKeys(MOBILE_FIELD, data.get("mobilenumber"));
    }

    public AccountConfirmationPage clickAccountCreate(){
        clickElem(CREATE_ACCOUNT_BTN);
        return new AccountConfirmationPage(driver);
    }

    public void fillCompleteRegistrationDetails(Map<String, String> data){
        selectTitle(data);
        fillAccountInformation(data);
        selectDateOfBirth(data);
        selectCheckBoxNewsletter();
        filAddressInformation(data);
    }




}
