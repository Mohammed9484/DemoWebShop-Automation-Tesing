package DemoWebShop.PageObjects;

import WebShop.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCart extends AbstractComponent {
    WebDriver driver;

    public ShoppingCart(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "product")
    List<WebElement> products;
    @FindBy(xpath = "//button[@id='checkout']")
    WebElement checkOutButton;
    @FindBy(xpath = "//input[@id='termsofservice']")
    WebElement termsButton;
    @FindBy(tagName = "p")
    WebElement errorMessage;
    @FindBy(xpath = "//div[@class='order-summary-content']")
    WebElement emptyCartMessage;


    public String checkProductAdded(String productName) {
        waitForWebElementToAppear(checkOutButton);
        WebElement pro = products.stream().filter(product -> product.findElement(By.tagName("a")).getText().equalsIgnoreCase(productName))
                .findAny().orElse(null);
        if (pro != null) {
            return pro.findElement(By.cssSelector(".product-name")).getText();
        } else {
            System.out.println("Product not found: " + productName);
            return null;
        }
    }

    public CheckOutPage checkOut() {
        waitForWebElementToAppear(checkOutButton);
        termsButton.click();
        checkOutButton.click();
        return new CheckOutPage(driver);
    }
    public CheckOutPage checkOutWithoutTermsButton() {
        waitForWebElementToAppear(checkOutButton);
        checkOutButton.click();
        return new CheckOutPage(driver);
    }
    public String getErrorMessage(){
        return errorMessage.getText();
    }
    public String getEptyCartMessage(){
        return emptyCartMessage.getText();
    }

}
