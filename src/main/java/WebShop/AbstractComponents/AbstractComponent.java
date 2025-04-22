package WebShop.AbstractComponents;

import java.time.Duration;

import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersHeader;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(ele));

	}


	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
//		w.until(ExpectedConditions.invisibilityOf(element));

	}

	
	


	
}
