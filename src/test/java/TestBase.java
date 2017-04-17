import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by David on 4/16/2017.
 * This class would evntually load properties from a config file specifying things such as webdriver type (chome, firefox, etc)
 * More helper methods to setup and teardown after tests are run,
 * Methods for integration with other frameworks, etc
 */
public class TestBase {

    private static WebDriver driver;

    //constructor to initialize a chrome driver to be used by tests
    public TestBase(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--disable-popup-blocking", "--disable-extensions", "--start-maximized");
        this.driver = new ChromeDriver(options);
    }

    //helper method to get the webdriver being used
    public WebDriver getDriver(){
        return this.driver;
    }

    //Kill browser after test
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
