package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

/**
 * Created by David on 4/16/2017.
 */
public class SouthwestSearchResultsPage extends WebPage {

    public SouthwestSearchResultsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "priceItinerarySubmit")
    @CacheLookup
    WebElement btnItinerarySubmit;


    /**
     * Method clicks the continue button after selecting flights
     */
    public void submitItinerary(){
        clickElement(btnItinerarySubmit, " submit button");
    }

    /**
     * helper method to determine if all prices for the flight are "unavailable"
     * @param flightRowSelector
     * @return
     */
    public int validatePriceAvailability(String flightRowSelector){
        //some flight rows don't have wanna get away prices. This determines how many price columns are shown for the flight in question
        int maxColumns =  driver.findElements(By.cssSelector(flightRowSelector + " > td")).size();
        int i;
        //last three columns (td elements) of the flight display the prices.  If prices are available, the divList will have child divs drilling down to
        //the actual price.  Otherwise it will only have a child span element.
        for (i = maxColumns; i >= 6; i--){
            List<WebElement> divList = driver.findElements(By.cssSelector(flightRowSelector + " > td:nth-child(" + i + ") > div > div"));
            if (divList.size() > 0) {
                return i;
            }
            else {
                String text = driver.findElement(By.cssSelector(flightRowSelector + " > td:nth-child(" + i + ") > div > span")).getText();
                if (!text.toLowerCase().contains("unavailable")) {//only a span element indicates the price is unavailable, move on to the next column for the flight row
                    return i;
                }
            }
        }
        return -1; //all price columns showed "unavailable" for the flight in question
    }

    /**
     * Selects the outbound portion of the flight with the cheapest fare if available
     */
    public boolean selectOutboundFlight(){
        String outboundTableSelector = "table[id='faresOutbound'] > tbody > tr[id*='outbound_flightRow']";
        List<WebElement> outboundFlights = driver.findElements(By.cssSelector(outboundTableSelector));//list of all outbound flight reported in the results
        int i = 1;
        String wannaGetAwaySelector = "";
        WebElement wannaGetAwayRadioButtonElement = null;
        int price = 0;
        int lowestPrice  = price;
        String priceStr = "0";
        //determine if all the price columns for the first flight show "unavailable"
        int priceColumnToUse  = validatePriceAvailability(outboundTableSelector + ":nth-child(" + i + ")");
        if (priceColumnToUse != -1) {
            wannaGetAwaySelector = "table[id='faresOutbound'] > tbody > tr[id*='outbound_flightRow']:nth-child(" + i + ") > td:nth-child(" + priceColumnToUse + ") > div > div > div > div > div";
            wannaGetAwayRadioButtonElement = driver.findElement(By.cssSelector(wannaGetAwaySelector + " > input"));
            priceStr = driver.findElement(By.cssSelector(wannaGetAwaySelector + " > label")).getText().trim().replace("$", "");
            price = Integer.parseInt(priceStr);
        }
        //for each flght in the outbound flights, determine if all prices show "unavailable" or if a price is found
        //hold on to the flight and the price to make sure it is the lowest price
        for (i = 2; i <= outboundFlights.size(); i++) {
            priceColumnToUse  = validatePriceAvailability(outboundTableSelector + ":nth-child(" + i + ")");
            if (priceColumnToUse != -1) {
                wannaGetAwaySelector = "table[id='faresOutbound'] > tbody > tr[id*='outbound_flightRow']:nth-child(" + i + ") > td:nth-child(" + priceColumnToUse + ") > div > div > div > div > div";
                priceStr = driver.findElement(By.cssSelector(wannaGetAwaySelector + " > label")).getText().trim().replace("$", "");
                price = Integer.parseInt(priceStr);
                //if the first flight in the results was completely "unavailable" and a price has been found on any subsequent flight
                //initially make that flights price the lowest flight.  If subsequent outbound flights have lower rates note those flights as the cheapest
                if (price < lowestPrice || lowestPrice == 0) {
                    lowestPrice = price;
                    wannaGetAwayRadioButtonElement = driver.findElement(By.cssSelector(wannaGetAwaySelector + " > input"));
                }
            }
        }

        if (wannaGetAwayRadioButtonElement != null) {
            clickElement(wannaGetAwayRadioButtonElement, " lowest fare radio button");//select the flight with the lowest price determined from logic above
            return true;
        }
        else{
            return false;//return false if all outbound flights showed "unavailable" so the test can fail at this point
        }
    }


    public boolean selectReturnFlight(){
        String returnTableSelector = "table[id='faresReturn'] > tbody > tr[id*='inbound_flightRow']";
        List<WebElement> returnFlights = driver.findElements(By.cssSelector(returnTableSelector));
        int i = 1;
        String wannaGetAwaySelector = "";
        WebElement wannaGetAwayRadioButtonElement = null;
        int price = 0;
        int lowestPrice  = price;
        String priceStr = "0";
        int priceColumnToUse  = validatePriceAvailability(returnTableSelector + ":nth-child(" + i + ")");
        //set the first return flight as the lowest fare if it is has a price available
        if (priceColumnToUse != -1) {
            wannaGetAwaySelector = "table[id='faresOutbound'] > tbody > tr[id*='outbound_flightRow']:nth-child(" + i + ") > td:nth-child(" + priceColumnToUse + ") > div > div > div > div > div";
            wannaGetAwayRadioButtonElement = driver.findElement(By.cssSelector(wannaGetAwaySelector + " > input"));
            priceStr = driver.findElement(By.cssSelector(wannaGetAwaySelector + " > label")).getText().trim().replace("$", "");
            price = Integer.parseInt(priceStr);
        }
        //for each flght in the outbound flights, determine if all prices show "unavailable" or if a price is found
        //hold on to the flight and the price to make sure it is the lowest price
        for (i = 2; i <= returnFlights.size(); i++) {
            priceColumnToUse  = validatePriceAvailability(returnTableSelector + ":nth-child(" + i + ")");
            if (priceColumnToUse != -1) {
                wannaGetAwaySelector = "table[id='faresReturn'] > tbody > tr[id*='inbound_flightRow']:nth-child(" + i + ") > td:nth-child(" + priceColumnToUse + ") > div > div > div > div > div";
                priceStr = driver.findElement(By.cssSelector(wannaGetAwaySelector + " > label")).getText().trim().replace("$", "");
                price = Integer.parseInt(priceStr);
                //if the first flight in the results was completely "unavailable" and a price has been found on any subsequent flight
                //initially make that flights price the lowest flight.  If subsequent outbound flights have lower rates note those flights as the cheapest
                if (price < lowestPrice || lowestPrice == 0) {
                    lowestPrice = price;
                    wannaGetAwayRadioButtonElement = driver.findElement(By.cssSelector(wannaGetAwaySelector + " > input"));
                }
            }
        }

        if (wannaGetAwayRadioButtonElement != null) {
            clickElement(wannaGetAwayRadioButtonElement, " lowest fare radio button");//select the flight with the lowest price determined from logic above
            return true;
        }
        else{
            return false;//return false if all outbound flights showed "unavailable" so the test can fail at this point
        }
    }



}
