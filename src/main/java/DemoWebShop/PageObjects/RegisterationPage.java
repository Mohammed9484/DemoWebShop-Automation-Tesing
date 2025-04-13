package DemoWebShop.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import WebShop.AbstractComponents.AbstractComponent;

public class RegisterationPage extends AbstractComponent {
	WebDriver driver;

	public RegisterationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='gender-male']")
	WebElement male;
	@FindBy(xpath = "//input[@id='gender-female']")
	WebElement female;

	@FindBy(xpath = "//input[@id='FirstName']")
	WebElement userFirstName;

	@FindBy(xpath = "//input[@id='LastName']")
	WebElement userLastName;
	@FindBy(xpath = "//input[@id='Email']")
	WebElement userEmail;
	@FindBy(xpath = "//input[@id='Password']")
	WebElement userPassword;
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	WebElement userConfirmPassword;
	@FindBy(xpath = "(//input[@type='submit'])[2]")
	WebElement registerButton;
	@FindBy(className = "register-continue-button")
	WebElement continueButton;
	@FindBy(xpath = "(//a[@class='account'])[1]")
	WebElement registerID;
	@FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div[1]/div[2]")
	WebElement registerMessage;
	@FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[1]")
	WebElement errorMessage;
	@FindBy(xpath = "//span[@for='FirstName']")
	WebElement firstNameErrorMessage;
	@FindBy(xpath = "//span[@for='LastName']")
	WebElement lastNameErrorMessage;
	@FindBy(xpath = "//span[@for='Email']")
	WebElement emailErrorMessage;
	@FindBy(xpath = "//span[@for='Password']")
	WebElement passwordErrorMessage;
	@FindBy(xpath = "//span[@for='ConfirmPassword']")
	WebElement confirmPasswordErrorMessage;

	public void registerApplication(String gender, String firstName, String lastName, String email, String password) {
		if (gender.equalsIgnoreCase("male")) {
			male.click();
		} else
			female.click();
		userFirstName.sendKeys(firstName);
		userLastName.sendKeys(lastName);
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		userConfirmPassword.sendKeys(password);
		registerButton.click();

		// continueButton.click();
	}

	public String getID() {
		waitForWebElementToAppear(continueButton);

		String id = registerID.getText();
		return id;

	}

	public String getConfirmationMessage() {
		waitForWebElementToAppear(continueButton);
		String id = registerMessage.getText();
		return id;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		String id = errorMessage.getText();
		return id;
	}

	public String getErrorMessageForField(String field) {
		String errorText = null;
		waitForWebElementToAppear(firstNameErrorMessage);

		switch (field) {
		case "firstname":
			errorText = firstNameErrorMessage.getText();
			break;
		case "lastname":
			errorText = lastNameErrorMessage.getText();
			break;
		case "email":
			errorText = emailErrorMessage.getText();
			break;
		case "password":
			errorText = passwordErrorMessage.getText();
			break;
		case "confirmation password":

			errorText = confirmPasswordErrorMessage.getText();
			break;
		}

		return errorText;
	}

}
