package tests;

import org.testng.annotations.Test;
import pages.*;

public class Tests_Contact_Page_Scenario extends BaseTest {

    @Test
    public void validateMandatoryFieldsErrorsScenario() throws Exception {

        LandingPage landingSteps = new LandingPage(driver);
        landingSteps.launchBrowser();
        landingSteps.clickTab("Contact");

        ContactPage addContactSteps = new ContactPage(driver);
        addContactSteps.clickSubmit();
        addContactSteps.validateMainError();
        addContactSteps.validateMandatoryFieldErrorsWithText();
        addContactSteps.enterMandatoryFields("ABC","XYZ","message");
        addContactSteps.validateMandatoryFieldErrorsNotDisplayed();
    }

    @Test
    public void validateSuccessfulSubmission() throws Exception {

        LandingPage landingSteps = new LandingPage(driver);
        landingSteps.launchBrowser();
        landingSteps.clickTab("Contact");

        ContactPage addContactSteps = new ContactPage(driver);
        addContactSteps.enterMandatoryFields("ABC","XYZ","message");
        addContactSteps.validateSuccessMsg();
    }
}
