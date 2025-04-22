package DemoWebShop.Tests;

import DemoWebShop.Data.DataDriven;
import DemoWebShop.PageObjects.ProductPage;
import DemoWebShop.TestComponents.BaseTest;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
@Listeners({io.qameta.allure.testng.AllureTestNg.class})

public class SearchTests extends BaseTest {


    // json data HashMap<String, String> input getDataJson
    // excel String param1, String param2, String param3 getDataExcel
    @Test(dataProvider = "validSearch", enabled = true, priority = 1)
    @Step("Searching for: {productName}")
    public void validSearch(String productName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
       homePage.searchAndSelect(productName,"laptop");
    }

    @Test(dataProvider = "invalidSearch", enabled = true, priority = 1)
    public void invalidSearch(String productName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        ProductPage productPage= homePage.search(productName);
        Assert.assertEquals(productPage.getErrorMessage(), "No products were found that matched your criteria.");
    }


    @DataProvider
    public Object[][] validSearch() throws IOException {

        DataDriven d = new DataDriven();
        List<List<String>> testData = d.getData("search");
        // Ensure we remove the "TestCase" column from the extracted data
        Object[][] data = new Object[testData.size()][];
        for (int i = 0; i < testData.size(); i++) {
            // Skip the first column (TestCase Name)
            data[i] = testData.get(i).subList(1, testData.get(i).size()).toArray(new String[0]);
        }
        return data;
    }

    @DataProvider
    public Object[][] invalidSearch() throws IOException {

        DataDriven d = new DataDriven();
        List<List<String>> testData = d.getData("invalid search");
        // Ensure we remove the "TestCase" column from the extracted data
        Object[][] data = new Object[testData.size()][];
        for (int i = 0; i < testData.size(); i++) {
            // Skip the first column (TestCase Name)
            data[i] = testData.get(i).subList(1, testData.get(i).size()).toArray(new String[0]);
        }
        return data;
    }

}
