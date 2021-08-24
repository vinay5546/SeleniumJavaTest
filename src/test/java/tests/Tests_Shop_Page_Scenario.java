package tests;

import org.testng.annotations.Test;
import pages.*;

import static common.Constants.*;


public class Tests_Shop_Page_Scenario extends BaseTest {

    @Test
    public void verifyCartMenu() throws Exception {
        LandingPage landingSteps = new LandingPage(driver);
        landingSteps.launchBrowser();
        landingSteps.clickTab("Shop");

        ShopPage shopPageSteps = new ShopPage(driver);
        shopPageSteps.clickBuyButton(6);
        shopPageSteps.clickBuyButton(6);
        shopPageSteps.clickBuyButton(4);
        shopPageSteps.clickCartMenu();
        shopPageSteps.verifyCartItems("Fluffy Cow",2);
        shopPageSteps.verifyCartItems("Fluffy Bunny",1);
    }

    @Test
    public void verifyCartMenuDetails() throws Exception {
        LandingPage landingSteps = new LandingPage(driver);
        landingSteps.launchBrowser();
        landingSteps.clickTab("Shop");

        ShopPage shopPageSteps = new ShopPage(driver);

        // Two Stuffed Frog
        shopPageSteps.clickBuyButton(2);
        shopPageSteps.clickBuyButton(2);

        // Five Fluffy Bunny
        shopPageSteps.clickBuyButton(4);
        shopPageSteps.clickBuyButton(4);
        shopPageSteps.clickBuyButton(4);
        shopPageSteps.clickBuyButton(4);
        shopPageSteps.clickBuyButton(4);

        // Three Valentine Bear
        shopPageSteps.clickBuyButton(7);
        shopPageSteps.clickBuyButton(7);
        shopPageSteps.clickBuyButton(7);

        shopPageSteps.clickCartMenu();
        shopPageSteps.verifyCartItemsPrice("Stuffed Frog", 10.99);
        shopPageSteps.verifyCartItemsPrice("Fluffy Bunny", 9.99);
        shopPageSteps.verifyCartItemsPrice("Valentine Bear", 14.99);

        shopPageSteps.verifyCartItemsSubtotal();
    }
}
