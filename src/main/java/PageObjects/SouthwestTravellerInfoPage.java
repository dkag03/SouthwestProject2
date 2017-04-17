package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by David on 4/16/2017.
 */
public class SouthwestTravellerInfoPage extends WebPage {

    public SouthwestTravellerInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    //Web element finders
    @FindBy(how = How.ID, using = "firstName0")
    @CacheLookup
    WebElement tbFirstName;

    @FindBy(how = How.ID, using = "lastName0")
    @CacheLookup
    WebElement tbLastName;

    @FindBy(how = How.ID, using = "dobMonth0")
    @CacheLookup
    WebElement selectMonth;

    @FindBy(how = How.ID, using = "dobDay0")
    @CacheLookup
    WebElement selectDay;

    @FindBy(how = How.ID, using = "dobYear0")
    @CacheLookup
    WebElement selectYear;

    @FindBy(how = How.ID, using = "gender0")
    @CacheLookup
    WebElement selectGender;

    @FindBy(how = How.ID, using = "js-email")
    @CacheLookup
    WebElement tbEmail;

    @FindBy(how = How.ID, using = "creditCardType")
    @CacheLookup
    WebElement selectCardType;

    @FindBy(how = How.ID, using = "creditCardNumber1")
    @CacheLookup
    WebElement tbCreditCardNumber;

    @FindBy(how = How.ID, using = "expirationMonth")
    @CacheLookup
    WebElement selectCreditCardExpMonth;

    @FindBy(how = How.ID, using = "expirationYear")
    @CacheLookup
    WebElement selectCreditCardExpYear;

    @FindBy(how = How.ID, using = "securityCode")
    @CacheLookup
    WebElement tbSecurityCode;

    @FindBy(how = How.ID, using = "creditCardFirstName")
    @CacheLookup
    WebElement tbCCFirstName;

    @FindBy(how = How.ID, using = "creditCardLastName")
    @CacheLookup
    WebElement tbCCLastName;

    @FindBy(how = How.ID, using = "creditCardAddress1")
    @CacheLookup
    WebElement tbCCAddress1;

    @FindBy(how = How.ID, using = "creditCardCity")
    @CacheLookup
    WebElement tbCCCity;

    @FindBy(how = How.ID, using = "creditCardState")
    @CacheLookup
    WebElement selectCCState;


    @FindBy(how = How.ID, using = "creditCardZip1")
    @CacheLookup
    WebElement tbCCZip;

    @FindBy(how = How.ID, using = "creditCardCountry")
    @CacheLookup
    WebElement selectCCCoutry;

    @FindBy(how = How.ID, using = "billerAreaCode")
    @CacheLookup
    WebElement tbPhoneAreaCode;

    @FindBy(how = How.ID, using = "billerPrefix")
    @CacheLookup
    WebElement tbPhonePrefix;

    @FindBy(how = How.ID, using = "billerUsPhoneLineNumber")
    @CacheLookup
    WebElement tbPhonePost;

    @FindBy(how = How.ID, using = "emailConfirmationAddress")
    @CacheLookup
    WebElement tbEmailConfirmation;

    @FindBy(how = How.ID, using = "visibleSubmitButton")
    @CacheLookup
    WebElement btnPurchase;


    /**
     * Method uses dummy data to fill out the traveller information fields
     */
    public void fillOutDummyTravellerInfo(){
        enterText(tbFirstName, "first name box", "John");
        enterText(tbLastName, "first name box", "Doe");
        selectByText(selectMonth, "month combobox", "1 - January");
        selectByText(selectDay, "day combobox", "1");
        selectByText(selectYear, "year combobox", "1980");
        selectByText(selectGender, "gender combobox", "Male");
        enterText(tbEmail, "email text box", "john.doe@gmail.com");
        selectByText(selectCardType, "cc type combobox", "Visa");
        enterText(tbCreditCardNumber, "cc number text box", "1234123412340964");
        selectByText(selectCreditCardExpMonth, "cc month combobox", "1 - January");
        selectByText(selectCreditCardExpYear, "cc year combobox", "2020");
        enterText(tbSecurityCode, "security code combo", "123");
        enterText(tbCCFirstName, "cc first name field", "John");
        enterText(tbCCLastName, "cc last name field", "Doe");
        enterText(tbCCAddress1, "cc address field", "1234 N Some Street");
        enterText(tbCCCity, "cc city field", "Tucson");
        selectByText(selectCCState, "cc state combobox", "Arizona");
        enterText(tbCCZip, "zip field", "85750");
        selectByText(selectCCCoutry, "cc country combobox", "UNITED STATES OF AMERICA - (US)");
        enterText(tbPhoneAreaCode, "area code field", "520");
        enterText(tbPhonePrefix, "phone prefix field", "123");
        enterText(tbPhonePost, "phone postfix field", "4567");
        enterText(tbEmailConfirmation, "confirmation email field", "john.doe@gmail.com");

    }


    /**
     * Method clicks the purchase button to finalize booking
     */
    public void clickSubmit(){
        clickElement(btnPurchase, "submit button");
    }





}
