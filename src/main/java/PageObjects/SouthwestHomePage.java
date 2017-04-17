package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by David on 4/15/2017.
 */
public class SouthwestHomePage extends WebPage{


    public SouthwestHomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }


    //Web element finders
    @FindBy(how = How.ID, using = "booking-form--flight-tab")
    @CacheLookup
    WebElement linkFlightTab;


    @FindBy(how = How.ID, using = "air-city-departure")
    @CacheLookup
    WebElement tbDepart;


    @FindBy(how = How.ID, using = "air-city-arrival")
    @CacheLookup
    WebElement tbArrive;

    @FindBy(how = How.ID, using = "air-date-departure")
    @CacheLookup
    WebElement tbDateDepart;

    @FindBy(how = How.ID, using = "air-date-return")
    @CacheLookup
    WebElement tbDateReturn;

    @FindBy(how = How.ID, using = "air-pax-count-adults")
    @CacheLookup
    WebElement tbAdults;

    @FindBy(how = How.ID, using = "air-pax-count-seniors")
    @CacheLookup
    WebElement tbSeniors;

    @FindBy(how = How.ID, using = "jb-booking-form-submit-button")
    @CacheLookup
    WebElement btnSearch;


    /**
     * Helper method to click on the "Flight" tab
     */
    public void clickFlightTab(){
        clickElement(linkFlightTab, "flight tab link");
    }

    /**
     * helper method sets the number of travellers if different from the default
     * @param number
     */
    public void selectNumberOfAdults(int number){
        String numberOfAdults = tbAdults.getAttribute("value");
        if (!numberOfAdults.contentEquals("" + number)){//only set the number of travellers if different from the default
            clickElement(tbAdults, "adults textbox");
            String plusButtonSelector = "div[id='jb-number-selector-more']";
            String lessButtonSelector = "div[id='jb-number-selector-less']";
            WebElement lessButtonElement = driver.findElement(By.cssSelector(lessButtonSelector));
           int i = 0;
            //bring the number of travellers to 0 before incrementing to the specified value
            while (!tbAdults.getAttribute("value").contentEquals("0") && i < 100) {
                clickElement(lessButtonElement, "minus button");
                i++;
            }
            //incremement the number of travellers to the specified value using the "+" button
            String departLabelSelector = "label[class*='js-origin-airport-static-label']";
            WebElement plusButtonElement = driver.findElement(By.cssSelector(plusButtonSelector));
            for (i = 0; i < number; i++){
                clickElement(plusButtonElement, "plus button");
            }
            WebElement departLabelElement = driver.findElement(By.cssSelector(departLabelSelector));
            clickElement(departLabelElement, "adult label");
        }
    }


    /**
     * Method to enter the departure location and select the correct one from the auto-sugest list
     * @param text
     * @return
     */
    public boolean enterDepartLocation(String text){
        String locationSelector = "ul[id='air-city-departure-menu'] > li";
        enterText(tbDepart, "from field", text);
        //get the elements in the autosuggest list and select the one that matches the specified location
        List<WebElement> locations = driver.findElements(By.cssSelector(locationSelector));
        if (locations.size() > 0){
            String location = "";
            for (int i = 0; i < locations.size(); i++){
                location = locations.get(i).getText();
                if (location.contains(text)){
                    clickElement(locations.get(i), " item from auto-suggest list");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to set the arrival location by selecting the specified location from the autosuggest list
     * @param text
     * @return
     */
    public boolean enterArriveLocation(String text){
        String locationSelector = "ul[id='air-city-arrival-menu'] > li";
        enterText(tbArrive, "from field", text);
        //get the elements in the autosuggest list and select the one that matches the specified locaiton
        List<WebElement> locations = driver.findElements(By.cssSelector(locationSelector));
        if (locations.size() > 0){
            String location = "";
            for (int i = 0; i < locations.size(); i++){
                location = locations.get(i).getText();
                if (location.contains(text)){
                    clickElement(locations.get(i), " item from auto-suggest list");
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Method to enter a departure date and a one week later return date
     * @param calendar
     */
    public void enterDepartDateAndReturnOneWeekLater(Calendar calendar){
        enterDepartDate(calendar);
        calendar.add(Calendar.DATE, 7);
        enterReturnDate(calendar);

    }

    /**
     * Method to enter a specified departure date
     * @param calendar
     */
    public void enterDepartDate(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String dateString = month + "/" + day;
        enterText(tbDateDepart, "from date text box", dateString);

    }

    /**
     * Method to enter a specified return date
     * @param calendar
     */
    public void enterReturnDate(Calendar calendar){
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        String dateString = month + "/" + day;
        enterText(tbDateReturn, "from date text box", dateString );


    }


    /**
     * Method to click the search button
     */
    public void clickSearch(){
        clickElement(btnSearch, "search button");
    }

}

