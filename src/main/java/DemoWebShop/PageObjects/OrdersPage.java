package DemoWebShop.PageObjects;

import WebShop.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='section order-item']")
    List<WebElement> orders;
    @FindBy(xpath = "//strong[contains(.,'Order #')]")
    WebElement orderNumber;

    public void selectOrder(String orderNumber) {
        WebElement ord = orders.stream().filter(order -> order.findElement(By.tagName("strong")).getText().contains(orderNumber)).findFirst().orElse(null);
        assert ord != null;
        ord.findElement(By.xpath("//input[@value='Details']")).click();
    }

    public String getOrderNumberFromOrderPage(){
       String text= orderNumber.getText();
        String[] parts=text.split("#");
        return parts[1];
    }
}
