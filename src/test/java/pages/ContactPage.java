package pages;

import common.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactPage extends AbstractPage{

    @FindBy(id = "forename")
    private WebElement forename;

    @FindBy(id = "forename-err")
    private WebElement forenameError;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "email-err")
    private WebElement emailError;

    @FindBy(id = "message")
    private WebElement message;

    @FindBy(id = "message-err")
    private WebElement messageError;

    @FindBy(id = "header-message")
    private WebElement mainErrorMessage;

    @FindBy(xpath = "//a[text()='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement successMsg;

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void validateMainError () {
        waitForElementToAppear(mainErrorMessage);
        Assert.assertEquals(mainErrorMessage.getText(), Constants.mainErrorMsgContactPage, "Main Error message displayed");
    }

    public void validateMandatoryFieldErrorsWithText () {
        waitForElementToAppear(forenameError);
        Assert.assertEquals(forenameError.getText(), Constants.forenameError, "Forename Error message displayed");

        waitForElementToAppear(emailError);
        Assert.assertEquals(emailError.getText(), Constants.emailError, "Email Error message displayed");

        waitForElementToAppear(messageError);
        Assert.assertEquals(messageError.getText(), Constants.messageError, "Message Error message displayed");
    }

    public void validateMandatoryFieldErrorsNotDisplayed () {
        Assert.assertFalse(mainErrorMessage.isDisplayed());
        Assert.assertFalse(forenameError.isDisplayed());
        Assert.assertFalse(emailError.isDisplayed());
        Assert.assertFalse(messageError.isDisplayed());
    }

    public void clickSubmit () {
        waitForElementToAppear(submitButton);
        submitButton.click();
    }

    public void enterMandatoryFields (String forenameText, String emailAddress, String messageText) {
        waitForElementToAppear(forename);
        forename.clear();
        forename.sendKeys(forenameText);

        waitForElementToAppear(email);
        email.clear();
        email.sendKeys(emailAddress);

        waitForElementToAppear(message);
        message.clear();
        message.sendKeys(messageText);
    }

    public void validateSuccessMsg () {
        waitForElementToAppear(successMsg);
        Assert.assertEquals(successMsg.getText(), Constants.successMsg, "Success message displayed");
    }

}
