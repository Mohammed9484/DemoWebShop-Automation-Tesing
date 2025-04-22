package DemoWebShop.Tests;

import DemoWebShop.Data.DataDriven;
import DemoWebShop.PageObjects.LoginPage;
import DemoWebShop.TestComponents.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
@Listeners(AllureTestNg.class)
public class LoginTests extends BaseTest {


    // json data HashMap<String, String> input getDataJson
    // excel String param1, String param2, String param3 getDataExcel
    //Test case1: Verify Login with valid email and valid password

    @Test(dataProvider = "validLogin", priority = 1)
    @Step("test")
    @Severity(SeverityLevel.CRITICAL)
    public void validLogin(String email, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        LoginPage loginPage = homePage.goToLogin();
        loginPage.loginApplication(email, password);
        Assert.assertEquals(loginPage.getID(), email);
        loginPage.logOut();
    }

    //Test case2: Verify Login with unregisterd email
    //Tes case3:Verify Login with wrong password
    @Test(dataProvider = "inValidLogin", priority = 2)
    public void inValidLogin(String email, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        LoginPage loginPage = homePage.goToLogin();
        loginPage.loginApplication(email, password);
        Assert.assertEquals(loginPage.getErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.");
    }

    @DataProvider
    public Object[][] validLogin() throws IOException {

        DataDriven d = new DataDriven();
        List<List<String>> testData = d.getData("Valid Login");
        // Ensure we remove the "TestCase" column from the extracted data
        Object[][] data = new Object[testData.size()][];
        for (int i = 0; i < testData.size(); i++) {
            // Skip the first column (TestCase Name)
            data[i] = testData.get(i).subList(1, testData.get(i).size()).toArray(new String[0]);
        }
        return data;
    }

    @DataProvider
    public Object[][] inValidLogin() throws IOException {

        DataDriven d = new DataDriven();
        List<List<String>> testData = d.getData("InValid Login");
        // Ensure we remove the "TestCase" column from the extracted data
        Object[][] data = new Object[testData.size()][];
        for (int i = 0; i < testData.size(); i++) {
            // Skip the first column (TestCase Name)
            data[i] = testData.get(i).subList(1, testData.get(i).size()).toArray(new String[0]);
        }
        return data;
    }

}
