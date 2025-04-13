package DemoWebShop.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebShop.AbstractComponents.AbstractComponent;

public class ProductPage extends AbstractComponent {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//strong[@class='result']")
	WebElement errorMessage;
	@FindBy(xpath = "//input[@class='button-1 add-to-cart-button']")
	WebElement addToCartButton;
	@FindBy(xpath = "//p[@class='content']")
	WebElement toastMessage;

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		String id = errorMessage.getText();
		return id;
	}
	public void addToCart() {
		waitForWebElementToAppear(addToCartButton);
		addToCartButton.click();
	}
	public String getConfirmationAddToCartMessage(){
		waitForWebElementToAppear(toastMessage);

		return toastMessage.getText();
	}




}
