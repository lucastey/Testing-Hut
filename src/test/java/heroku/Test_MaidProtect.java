package heroku;

import java.util.*;
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

    List<String> discountedPremium = new ArrayList<String>();
    List<String> actualPremium = new ArrayList<String>();

    @BeforeTest
    public WebDriver webDriverSetup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/lucastay/Desktop/Misc/chromedriver_mac64/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        //DEFAULT TO GOOGLE
        driver.get("https://google.com/");

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
        Thread.sleep(500);
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
        Thread.sleep(200);
        WebElement todayOption = driver.findElement(By.xpath("//*[@id=\"mat-datepicker-1\"]/div/mat-month-view/table/tbody/tr[4]/td[5]"));
        todayOption.click();

        //select get started button
        WebElement getStartedButton = driver.findElement(By.xpath("//*[@id=\"btn-get-started\"]"));
        getStartedButton.click();

        WebElement selectPlanTitle = driver.findElement(By.xpath("//*[@id=\"plan-label-select-plan\"]"));
        Assert.assertEquals(selectPlanTitle.getText(), "SELECT PLAN");
    }

    @Test
    void selectPlan() {
        //retrieve discounted premium + strikeoff premium
        WebElement discountPremium = driver.findElement(By.xpath("/html/body/app-root/page-plan/div/div/div/div[1]/div[1]/div[3]/div/div/div/div[2]/div"));
        WebElement strikeoffPremium = driver.findElement(By.xpath("/html/body/app-root/page-plan/div/div/div/div[1]/div[1]/div[3]/div/div/div/div[3]"));

        //append into string array?
        discountedPremium.add(discountPremium.getText());
        actualPremium.add(strikeoffPremium.getText());

        System.out.println(discountedPremium);
        System.out.println(actualPremium);
    }

    @AfterTest
    public void webDriverTearDown(XmlTest xmlTest){
        driver.quit();
    }
}
