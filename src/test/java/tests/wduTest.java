package tests;

import base.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.wduPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class wduTest extends BaseTestClass {
    wduPage wdu_Page;

    @BeforeMethod
    public void setUp() {
        initBrowserAndNavigateToUrl();
    }

    @Test(priority = 1, invocationCount = 10)
    public void verifyAjaxLoaderPopUpTest() throws InterruptedException {
        wdu_Page = new wduPage();
        scrollToElement(wdu_Page.ajaxLoaderTitle);
        Assert.assertTrue(wdu_Page.ajaxLoaderTitle.isDisplayed());
        String originalWindow = driver.getWindowHandle();
        wdu_Page.ajaxLoaderTitle.click();
        Thread.sleep(7000);
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        waitForElement(wdu_Page.ClickMe);
        wdu_Page.ClickMe.click();
        String popUpBody = "The waiting game can be a tricky one; this exercise will hopefully " +
                "improve your understandings of the various types of waits.";
        waitForElement(wdu_Page.PopUpHeader);
        Assert.assertTrue(wdu_Page.PopUpHeader.isDisplayed());
        Assert.assertEquals(popUpBody, wdu_Page.PopUpBody.getText());
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        takeScreenshot();
        driver.quit();
    }
}
