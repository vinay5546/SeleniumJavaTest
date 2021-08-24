package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.util.List;

public class ShopPage extends AbstractPage {

    @FindBy(xpath = "//strong[@class='total ng-binding']")
    private WebElement total;

    @FindBy(xpath = "//table[@class='table table-striped cart-items']")
    private WebElement cartItemsTable;

    @FindBy(id = "nav-cart")
    private WebElement cartMenu;

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public void clickBuyButton(int productNumber) {
        WebElement buy = driver.findElement(By.xpath("//li[@id='product-"+productNumber+"']//a[text()='Buy']"));
        buy.click();
    }

    public void clickCartMenu() {
        cartMenu.click();
    }

    public void verifyCartItems(String itemName, int noOfTtems) {
        waitForElementToAppear(cartItemsTable);
        List<WebElement> allItemsRows = cartItemsTable.findElements(By.tagName("tr"));
        for (WebElement row:allItemsRows) {
            WebElement item = row.findElement(By.xpath("//td[1]"));
            if(item.getText().contains(itemName)) {
                Assert.assertEquals(itemName, item.getText(), "Item is there in the cart");
                WebElement quantityData = row.findElement(By.xpath("//td[@name='quantity']"));
                Assert.assertEquals(noOfTtems, Integer.parseInt(quantityData.getText()), "Item quantity is correct");
                break;
            }
        }
    }

    public void verifyCartItemsPrice(String itemName, Double expectedPrice) {
        Double actualPrice, actualSubtotal = 0.0;
        int quantity = 0;
        waitForElementToAppear(cartItemsTable);
        List<WebElement> allItemsRows = cartItemsTable.findElements(By.tagName("tr"));
        for (WebElement row:allItemsRows) {
            WebElement item = row.findElement(By.xpath("//td[1]"));
            if(item.getText().contains(itemName)) {

                WebElement priceData = row.findElement(By.xpath("//td[2]"));
                actualPrice = Double.parseDouble(priceData.getText().replace("$",""));
                Assert.assertEquals(expectedPrice, actualPrice, "Price is correct");

                WebElement quantityData = row.findElement(By.xpath("//td[@name='quantity']"));
                quantity = Integer.parseInt(quantityData.getText());
                Double subtotalCal = actualPrice * quantity;

                WebElement subtotalData = row.findElement(By.xpath("//td[4]"));
                actualSubtotal = Double.parseDouble(subtotalData.getText().replace("$",""));

                Assert.assertEquals(actualSubtotal,subtotalCal, "Subtotal is correct");
                break;
            }
        }
    }

    public void verifyCartItemsSubtotal() {
        Double subtotalCal= 0.0;
        waitForElementToAppear(cartItemsTable);
        List<WebElement> allItemsRows = cartItemsTable.findElements(By.tagName("tr"));
        for (WebElement row:allItemsRows) {
            WebElement subtotalData = row.findElement(By.xpath("//td[4]"));
            subtotalCal = subtotalCal + Double.parseDouble(subtotalData.getText().replace("$",""));
        }
        Double actualSubtotal = Double.parseDouble(total.getText());
        DecimalFormat df = new DecimalFormat("#.#");
        df.format(actualSubtotal);
        Assert.assertEquals(subtotalCal,actualSubtotal, "Subtotal is correct");
    }

}
