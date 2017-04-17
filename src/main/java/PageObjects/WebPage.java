package PageObjects;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


/**
 * Created by David on 4/15/2017. A basic webpage with some wrapper methods to interact with web elements and print to the console
 */

public class WebPage {

    public WebDriver driver;


    //constructor stores an instance of the webdriver and initializes all webelements on the page
    public WebPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     * Method clears text field elements and enters specified text while printing action to the console
     * @param element
     * @param webElementDesc
     * @param text
     */
    public void enterText(WebElement element, String webElementDesc, String text){
        System.out.println("\nEntering '" + text + "' into " + webElementDesc);
        sleepSomeSeconds(1);//webdriver acts funny sometimes and gets ahead of the page
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Method selects text from a combobox element while printing action to the console
     * @param element
     * @param webElementDesc
     * @param text
     */
   public void selectByText(WebElement element, String webElementDesc, String text){
       System.out.println("\nSelecting option '" + text + "' from " + webElementDesc);
       Select sel = new Select(element);
       sel.selectByVisibleText(text);
   }


    /**
     * Method clicks on a webelement while printing action to the console
     * @param element
     * @param webElementDesc
     */
    public void clickElement(WebElement element, String webElementDesc){
        System.out.println("\nClicking on " + webElementDesc );
        element.click();
    }

    /**
     * Method sleeps for a specified number of seconds before proceeding
     * @param numberOfSeconds
     */
    public void sleepSomeSeconds(int numberOfSeconds){
        try{
            Thread.sleep(numberOfSeconds * 1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isTextOnPageNotCaseSensitive(String text){
        return driver.getPageSource().toLowerCase().contains(text.toLowerCase());
    }

}








