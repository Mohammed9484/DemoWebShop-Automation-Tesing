package DemoWebShop.PageObjects;

import WebShop.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponent {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = "//input[@id='Email']")
	WebElement userEmail;
	@FindBy(xpath = "//input[@id='Password']")
	WebElement userPassword;
	@FindBy(xpath = "(//a[@class='account'])[1]")
	WebElement registerID;
	@FindBy(xpath = "//input[@value='Log in']")
	WebElement logInButton;
	@FindBy(xpath = "(//span[contains(text(),'Login was unsuccessful. Please correct the errors ')])[1]")
	WebElement errorMessage;




	public void loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		logInButton.click();

	}

	public String getID() {
		waitForWebElementToAppear(registerID);

		String id = registerID.getText();
		return id;

	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		String id = errorMessage.getText();
		return id;
	}



}
