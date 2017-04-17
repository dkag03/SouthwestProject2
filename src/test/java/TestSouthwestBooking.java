import PageObjects.SouthwestHomePage;
import PageObjects.SouthwestPriceReservationsPage;
import PageObjects.SouthwestSearchResultsPage;
import PageObjects.SouthwestTravellerInfoPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by David on 4/15/2017.
 */
public class TestSouthwestBooking extends TestBase {


    /**
     * This tests if a user can successfully book the cheapest flight leaving today from IAD to LAX and returning in a week on southwest.com
     */
    @Test
    public void testFlightBooking() {
        //set up chrome driver options

        WebDriver myDriver = getDriver();
        myDriver.get("http://www.southwest.com"); //navigate to southwest url

        //open home page, click flight tab to be sure and fill out flight search information
        //use today's date as depart and a week later as return date
        SouthwestHomePage homePage = new SouthwestHomePage(myDriver);
        homePage.clickFlightTab();
        homePage.enterDepartLocation("IAD");
        homePage.sleepSomeSeconds(1);
        homePage.enterArriveLocation("LAX");
        Calendar cal = new GregorianCalendar();
        homePage.enterDepartDateAndReturnOneWeekLater(cal);
        homePage.selectNumberOfAdults(1);
        homePage.clickSearch();

        //select cheapest fare if available or fail test if flights are not available on chosen dates
        SouthwestSearchResultsPage resultsPage = new SouthwestSearchResultsPage(myDriver);
        Assert.assertTrue("No outbound flights available for selected depart date", resultsPage.selectOutboundFlight());
        Assert.assertTrue("No return flights available for selected return date", resultsPage.selectReturnFlight());
        resultsPage.submitItinerary();

        //Check the cart and submit the itinerary
        SouthwestPriceReservationsPage priceReservationsPage = new SouthwestPriceReservationsPage(myDriver);
        priceReservationsPage.clickPriceSubmit();


        //Fill out dummy traveller info and attempt to book the flight
        SouthwestTravellerInfoPage travellerInfoPage = new SouthwestTravellerInfoPage(myDriver);
        travellerInfoPage.fillOutDummyTravellerInfo();
        travellerInfoPage.clickSubmit();

        //Since I'm using dummy data, I expect the booking to fail due to bad credit card info
        Assert.assertTrue("This booking should not have gone through with dummy data", travellerInfoPage.isTextOnPageNotCaseSensitive( "The credit card number is invalid"));






    }
}
