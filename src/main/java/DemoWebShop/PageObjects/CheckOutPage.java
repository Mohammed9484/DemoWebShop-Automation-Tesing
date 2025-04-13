package DemoWebShop.PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebShop.AbstractComponents.AbstractComponent;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[text()='Checkout']")
    WebElement confirm;
    //@FindBy(xpath = "//h2[text()='Billing address']")
//	WebElement confirm;
    @FindBy(xpath = "//input[@id='BillingNewAddress_FirstName']")
    WebElement userFirstName;
    @FindBy(xpath = "//input[@id='BillingNewAddress_LastName']")
    WebElement userLastName;
    @FindBy(xpath = "//input[@id='BillingNewAddress_Email']")
    WebElement userEmail;
    @FindBy(xpath = "//select[@id='BillingNewAddress_CountryId']")
    WebElement countryDropdown;
    Select countryDropMenu = new Select(countryDropdown);
    @FindBy(xpath = "//input[@id='BillingNewAddress_City']")
    WebElement city;
    @FindBy(xpath = "//input[@id='BillingNewAddress_Address1']")
    WebElement address;
    @FindBy(xpath = "//input[@id='BillingNewAddress_ZipPostalCode']")
    WebElement postalCode;
    @FindBy(xpath = "//input[@id='BillingNewAddress_PhoneNumber']")
    WebElement phone;
    @FindBy(xpath = "//input[@onclick='Billing.save()']")
    WebElement billingContinueButton;
    @FindBy(xpath = "//input[@onclick='Shipping.save()']")
    WebElement shippingContinueButton;
    @FindBy(xpath = "//input[@class='button-1 shipping-method-next-step-button']")
    WebElement shippingMethodContinueButton;
    @FindBy(xpath = "//input[@class='button-1 payment-method-next-step-button']")
    WebElement paymentContinueButton;
    @FindBy(xpath = "//input[@class='button-1 payment-info-next-step-button']")
    WebElement paymentInfoContinueButton;
    @FindBy(xpath = "//input[@value='Confirm']")
    WebElement confirmButton;
    @FindBy(xpath = "//strong")
    WebElement confirmationMessage;
    @FindBy(css = "div[class='title'] strong")
    WebElement continueButton;
    @FindBy(xpath = "//select[@id='billing-address-select']")
    WebElement addressMenu;
    Select addressDropMenu = new Select(addressMenu);

    public String confirm() {
        return confirm.getText();

    }

    public void checkoutForm(String firstName, String lastName, String country, String email, String userCity, String userAddress, String zipCode, String phoneNumber) {

    waitForWebElementToAppear(billingContinueButton);
    if (!userFirstName.isDisplayed()) {
        addressDropMenu.selectByVisibleText("New Address");
    }
        userFirstName.clear();
        userFirstName.sendKeys(firstName);
        userLastName.clear();
        userLastName.sendKeys(lastName);
        userEmail.clear();
        userEmail.sendKeys(email);
        countryDropMenu.selectByVisibleText(country);
        city.sendKeys(userCity);
        address.sendKeys(userAddress);
        postalCode.sendKeys(zipCode);
        phone.sendKeys(phoneNumber);


        billingContinueButton.click();
        waitForWebElementToAppear(shippingContinueButton);
        shippingContinueButton.click();
        waitForWebElementToAppear(shippingMethodContinueButton);
        shippingMethodContinueButton.click();
        waitForWebElementToAppear(paymentContinueButton);
        paymentContinueButton.click();
        waitForWebElementToAppear(paymentInfoContinueButton);
        paymentInfoContinueButton.click();
        waitForWebElementToAppear(confirmButton);
        confirmButton.click();
        waitForWebElementToAppear(continueButton);

        continueButton.click();
    }

    public String getConfirmMessgae() {
        waitForWebElementToAppear(confirmationMessage);
        return confirmationMessage.getText();

    }
}
