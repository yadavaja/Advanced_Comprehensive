package pages;

import base.BaseTestClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class wduPage extends BaseTestClass {

    public wduPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[id='ajax-loader']")
    public WebElement ajaxLoaderTitle;

    @FindBy(css = "[data-target='#myModalClick']")
    public WebElement ClickMe;

    @FindBy(css = "[class='modal-title']")
    public WebElement PopUpHeader;

    @FindBy(xpath = "//*[@class='modal-body']//p")
    public WebElement PopUpBody;

}
