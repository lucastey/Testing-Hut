package heroku;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    Base base = new Base();
    @BeforeTest
    void setUp(){
        base.webDriverSetup();
    }
    @AfterTest
    void tearDown(WebDriver driver){
        base.webDriverTearDown(driver);
    }
}
