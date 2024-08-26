package base;

import base.BaseTestClass;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.IOException;

public class Hooks extends BaseTestClass {

    @Before
    public void setUp() {
        initBrowserAndNavigateToUrl();
    }

    @After
    public void tearDown() throws IOException {
        takeScreenshot();
        driver.quit();
        System.out.println("Browser is closed");
    }
}
