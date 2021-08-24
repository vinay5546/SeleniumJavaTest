package pages;

import common.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LandingPage extends AbstractPage {

    @FindBy(id = "nav-contact")
    private WebElement contactTab;

    @FindBy(id = "nav-home")
    private WebElement homeTab;

    @FindBy(id = "nav-shop")
    private WebElement shopTab;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void launchBrowser() {
        driver.get(Constants.URL);
        Assert.assertEquals(driver.getCurrentUrl(), Constants.URL, "URL Mismatch");
    }

    public void clickTab(String tabName) {
        if(tabName == "Home") {
            waitForElementToAppear(homeTab);
            homeTab.click();
        } else if (tabName == "Shop") {
            waitForElementToAppear(shopTab);
            shopTab.click();
        } else if (tabName == "Contact") {
            waitForElementToAppear(contactTab);
            contactTab.click();
        } else {
            System.out.println("Invalid Tab Name:"+ tabName);
        }
    }
}
