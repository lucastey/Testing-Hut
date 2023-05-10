package heroku;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.xml.XmlTest;

public class Test_BugSite {
    WebDriver driver;

    @BeforeTest
    public WebDriver webDriverSetup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/lucastay/Desktop/Misc/chromedriver_mac64/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.google.com");

        return driver;
    }

    @Test
    public void test1(){
        //locating the google icon and getting the alt attribute
        String googleTitle = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/img")).getAccessibleName();
        Assert.assertEquals(googleTitle, "Google");
    }

    @Test
    public void test2(){
        //locating the get lucky button and clicking it
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[2]")).click();
        String currentURL = driver.getCurrentUrl();
        //check if the redirection actually works via expected URL
        Assert.assertEquals(currentURL, "https://www.google.com/doodles");
    }

    @Test
    public void test3() throws InterruptedException {
        //populate search field after navigating into page
        WebElement searchField = driver.findElement(By.xpath("//*[@id=\"searchinput\"]"));
        searchField.sendKeys("wood");
        searchField.submit();
        Thread.sleep(1500);
        //check if URL updates
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com/doodles?q=wood");
    }

    @Test
    public void test4(){
        WebElement swedenItem = driver.findElement(By.xpath("//*[contains(text(), 'Sweden National Day 2022')]"));
        swedenItem.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com/doodles/sweden-national-day-2022");
    }

    @Test
    public void test5(){
        WebElement dropdownLanguage = driver.findElement(By.xpath("//*[@id=\"lang-chooser\"]"));
        Select select = new Select(dropdownLanguage);
        select.selectByValue("fi");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com/doodles/sweden-national-day-2022?hl=fi");
    }

    @AfterTest
    public void webDriverTearDown(XmlTest xmlTest){
        driver.quit();
    }


}
