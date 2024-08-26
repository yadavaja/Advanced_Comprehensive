package stepDefinition;

import base.BaseTestClass;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import pages.wduPage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class wdu_SD extends BaseTestClass {

    wduPage wdu_Page;
    String originalWindow;

    @Given("^Navigate to \"([^\\\"]*)\"$")
    public void Navigate_To_Application(String url) throws Throwable {
        loadProperties();
        String browserName = prop.getProperty("Browser");
        if (browserName.equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Then("^Verify AJAX LOADER is present or not using Assertions$")
    public void Verify_AJAX_LOADER() throws Throwable {
        wdu_Page = new wduPage();
        scrollToElement(wdu_Page.ajaxLoaderTitle);
        Assert.assertTrue(wdu_Page.ajaxLoaderTitle.isDisplayed());
    }

    @And("^Click on AJAX LOADER link$")
    public void Click_AJAX_LOADER() throws InterruptedException {
        waitForElement(wdu_Page.ajaxLoaderTitle);
        originalWindow = driver.getWindowHandle();
        wdu_Page.ajaxLoaderTitle.click();
        Thread.sleep(7000);
    }

    @Then("^Click on Click Me button$")
    public void Click_ClickMe() {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        waitForElement(wdu_Page.ClickMe);
        wdu_Page.ClickMe.click();
    }

    @And("^verify the pop-up is present or not$")
    public void Verify_Popup() {
        String popUpBody = "The waiting game can be a tricky one; this exercise will hopefully " +
                "improve your understandings of the various types of waits.";
        waitForElement(wdu_Page.PopUpHeader);
        Assert.assertTrue(wdu_Page.PopUpHeader.isDisplayed());
        Assert.assertEquals(popUpBody, wdu_Page.PopUpBody.getText());
    }

    @And("^Close the browser$")
    public void CloseBrowser() {
        driver.close();
        driver.switchTo().window(originalWindow);
        driver.quit();
    }

}
