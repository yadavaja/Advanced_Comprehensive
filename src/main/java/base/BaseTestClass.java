package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class BaseTestClass {
    public static WebDriver driver;
    public static Properties prop;

    public void initBrowserAndNavigateToUrl() {
        loadProperties();
        String browserName = prop.getProperty("Browser");
        if (browserName.equalsIgnoreCase("Chrome")) {
        	WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(prop.getProperty("URL"));
    }

    public void loadProperties() {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\data\\configData.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\Screenshots\\" + "Test_" + System.currentTimeMillis() + ".png"));
    }

    public void scrollToElement(WebElement ele) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
        Thread.sleep(500);
    }

    public void waitForElement(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }
}
