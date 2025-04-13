package DemoWebShop.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebShop.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	//	driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	@FindBy(xpath = "(//button[normalize-space()='Checkout'])[1]")
	WebElement checkOut;
	public List<WebElement> getCartProduct() {
		return cartProducts;
	}

//	Boolean match = cartProducts.stream()
//			.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
//	Assert.assertTrue(match);

	public Boolean verifyCartProducts(String productName) {

		Boolean match = getCartProduct().stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckout() {
		checkOut.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
		
	}
}
