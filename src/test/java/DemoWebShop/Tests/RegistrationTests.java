package DemoWebShop.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import DemoWebShop.Data.DataDriven;
import DemoWebShop.PageObjects.RegisterationPage;
import DemoWebShop.TestComponents.BaseTest;

public class RegistrationTests extends BaseTest {

	public static String email1 = "testuser@example.com";
	// json data HashMap<String, String> input getDataJson
	// excel String param1, String param2, String param3 getDataExcel
	@Test(dataProvider = "validRegister", enabled = true, priority = 1)
	public void validRegister(String gender, String firstName, String lastName, String email, String password) {
	email1=email;

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		RegisterationPage registerationPage = homePage.goToRegisteration();
		registerationPage.registerApplication(gender, firstName, lastName, email, password);
		Assert.assertEquals(registerationPage.getID(), email);
		Assert.assertEquals(registerationPage.getConfirmationMessage(), "Your registration completed");
	}

	@Test(dataProvider = "inValidRegister", enabled = true, priority = 2)
	public void registerWithRegisteredEmail(String gender, String firstName, String lastName, String email,
											String password) throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		RegisterationPage registerationPage = homePage.goToRegisteration();
		registerationPage.registerApplication(gender, firstName, lastName, email, password);
		Assert.assertEquals(registerationPage.getErrorMessage(), "The specified email already exists");
	}

	@Test(enabled = true, priority = 2)
	public void registerWithEmptyFields() throws InterruptedException, IOException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		RegisterationPage registerationPage = homePage.goToRegisteration();
		registerationPage.registerApplication("", "", "", "", "");
		Assert.assertEquals(registerationPage.getErrorMessageForField("firstname"), "First name is required.");
		Assert.assertEquals(registerationPage.getErrorMessageForField("lastname"), "Last name is required.");
		Assert.assertEquals(registerationPage.getErrorMessageForField("email"), "Email is required.");
		Assert.assertEquals(registerationPage.getErrorMessageForField("password"), "Password is required.");
		Assert.assertEquals(registerationPage.getErrorMessageForField("confirmation password"), "Password is required.");
	}

	@DataProvider
	public Object[][] validRegister() throws IOException {

		DataDriven d = new DataDriven();
		List<List<String>> testData = d.getData("Valid Register");
		// Ensure we remove the "TestCase" column from the extracted data
		Object[][] data = new Object[testData.size()][];
		for (int i = 0; i < testData.size(); i++) {
			// Skip the first column (TestCase Name)
			data[i] = testData.get(i).subList(1, testData.get(i).size()).toArray(new String[0]);
		}
		return data;
	}

	@DataProvider
	public Object[][] inValidRegister() throws IOException {

		DataDriven d = new DataDriven();
		List<List<String>> testData = d.getData("Invalid Register with registered email");
		// Ensure we remove the "TestCase" column from the extracted data
		Object[][] data = new Object[testData.size()][];
		for (int i = 0; i < testData.size(); i++) {
			// Skip the first column (TestCase Name)
			data[i] = testData.get(i).subList(1, testData.get(i).size()).toArray(new String[0]);
		}
		return data;
	}

}
