package DemoWebShop.PageObjects;


import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebShop.AbstractComponents.AbstractComponent;
import org.testng.annotations.Listeners;

import java.util.List;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class HomePage extends AbstractComponent {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //	WebElement userEmail = driver.findElement(By.id("userEmail"));
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(xpath = "//a[@class='ico-register']")
    WebElement RegisterHeader;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement submitButton;
    //div[aria-label='Incorrect email or password.']
    @FindBy(css = "div[aria-label='Incorrect email or password.']")
    WebElement errorMessage;
    @FindBy(xpath = "//a[@class='ico-login']")
    WebElement loginHeader;
    @FindBy(xpath = "//input[@id='small-searchterms']")
    WebElement searchHeader;
    @FindBy(xpath = "//input[@value='Search']")
    WebElement searchButton;
    @FindBy(className = "ui-menu-item")
    List<WebElement> searchList;
    @FindBy(xpath = "//div[@class='item-box']")
    List<WebElement> products;
    @FindBy(xpath = "//p[@class='content']")
    WebElement toastMessage;
    @FindBy(xpath = "(//span[@class='cart-label'])[1]")
    WebElement shoppingCartHeader;
    @FindBy(css = "a[href='/customer/orders']")
    WebElement ordersFooter;

    By addToCart = By.xpath(".//input[@value='Add to cart']");


    public RegisterationPage goToRegisteration() {
        Allure.step("Go to registration page");
        RegisterHeader.click();
        RegisterationPage registerationPage = new RegisterationPage(driver);
        return registerationPage;

    }

    public LoginPage goToLogin() {
        Allure.step("Go to login page");
        loginHeader.click();
        return new LoginPage(driver);

    }

    public ShoppingCart goToShoppingCart() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        shoppingCartHeader.click();
        return new ShoppingCart(driver);

    }

    public OrdersPage goToOrders() {
        ordersFooter.click();
        return new OrdersPage(driver);

    }

    public ProductPage search(String productName) {
        searchHeader.sendKeys(productName);
        searchButton.click();
        return new ProductPage(driver);
    }

    public ProductPage searchAndSelect(String productName, String wantedProduct) {
        searchHeader.sendKeys(productName);
        waitForElementToAppear(By.className("ui-menu-item"));
        for (WebElement item : searchList) {
            if (item.getText().toLowerCase().contains(wantedProduct)) {
                item.click();
                waitForElementToAppear(By.cssSelector("h1[itemprop='name']"));
                break;
            }
        }
        return new ProductPage(driver);


    }

    public ProductPage addToCartFromHome(String productName) {
        WebElement prod = products.stream().filter(product -> product.findElement(By.xpath(".//h2[@class='product-title']/a"))
                .getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        if (prod != null) {
            WebElement element = prod.findElement(addToCart);
            element.click();
            waitForWebElementToAppear(toastMessage);
        } else {
            System.out.println("Product not found: " + productName);
        }
        return new ProductPage(driver);
    }


    public void goTo() {
        driver.get("https://demowebshop.tricentis.com/");

    }


}
