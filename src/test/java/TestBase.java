import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by David on 4/16/2017.
 */
public class TestBase {

    private static WebDriver driver;

    public TestBase(){
        System.setProperty("webdriver.chrome.driver", "C:\\Programming_Home\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--disable-popup-blocking", "--disable-extensions", "--start-maximized");
        this.driver = new ChromeDriver(options);
    }

    public WebDriver getDriver(){
        return this.driver;
    }


    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
