package heroku;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.xml.XmlTest;

public class Test_MaidProtect {
    WebDriver driver;

    @BeforeTest
    public WebDriver webDriverSetup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/lucastay/Desktop/Misc/chromedriver_mac64/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        //DEFAULT TO GOOGLE
        driver.get("https://google.com");

        return driver;
    }

    @Test
    void testDrive() throws InterruptedException {
        Thread.sleep(500);
        String pageTitle = driver.findElement(By.xpath("//*[@id=\"lbl-navbar-title\"]")).getText();
        Assert.assertEquals(pageTitle, "MAID PROTECT");
    }

    @Test
    void LandingPage() throws InterruptedException {
        //to scroll to dropdown element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 700)");

        //select work permit dropdown and option
        WebElement workPermit = driver.findElement(By.xpath("//*[@id=\"maid-work-permit-type\"]/div/div[1]/div"));
        workPermit.click();
        WebElement renewalOption = driver.findElement(By.xpath("//*[@id=\"maid-work-permit-type\"]/div/div[1]/div/div/div/div[2]"));
        renewalOption.click();
        //Thread.sleep(1000);

        //select nationality option
        WebElement maidNationality = driver.findElement(By.xpath("//*[@id=\"autocomplete-maid-nationality\"]/mat-form-field/div/div[1]/div/span[1]"));
        maidNationality.click();
        WebElement indonesianOption = driver.findElement(By.xpath("//*[@id=\"autocomplete-maid-nationality\"]/mat-form-field/div/div[1]/div/div/div/div[3]"));
        indonesianOption.click();

        //select policy start date
        WebElement policyStartDate = driver.findElement(By.xpath("//*[@id=\"policy-start-date\"]/div/div[1]/div[1]"));
        policyStartDate.click();
        WebElement todayOption = driver.findElement(By.xpath("//*[@id=\"mat-datepicker-0\"]/div/mat-month-view/table/tbody/tr[4]/td[4]"));
        todayOption.click();

        //select get started button
        WebElement getStartedButton = driver.findElement(By.xpath("//*[@id=\"btn-get-started\"]"));
        getStartedButton.click();

        WebElement selectPlanTitle = driver.findElement(By.xpath("//*[@id=\"plan-label-select-plan\"]"));
        Assert.assertEquals(selectPlanTitle.getText(), "SELECT PLAN");
    }

    @AfterTest
    public void webDriverTearDown(XmlTest xmlTest){
        driver.quit();
    }
}
