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
public class SouthwestPriceReservationsPage extends WebPage {

    public SouthwestPriceReservationsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Web Element finders
    @FindBy(how = How.ID, using = "priceSubmitButton")
    @CacheLookup
    WebElement btnContinue;

    /**
     * Method to click the submit button for the flight reservation
     */
    public void clickPriceSubmit(){
        clickElement(btnContinue, " continue button");
    }
}
