package DemoWebShop.Tests;

import DemoWebShop.Data.DataDriven;
import DemoWebShop.PageObjects.CheckOutPage;
import DemoWebShop.PageObjects.ProductPage;
import DemoWebShop.PageObjects.ShoppingCart;
import DemoWebShop.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class CartTests extends BaseTest {


    @Test(dataProvider = "addToCart", enabled = true, priority = 1)
    public void verifyCheckOutFunctionality(String productName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        ProductPage productPage = homePage.addToCartFromHome(productName);
        Assert.assertEquals(productPage.getConfirmationAddToCartMessage(), "The product has been added to your shopping cart");
        ShoppingCart shoppingCart = homePage.goToShoppingCart();
        Assert.assertEquals(shoppingCart.checkProductAdded(productName), productName);
        shoppingCart.checkOut();
        //the following assertion works only when user is logged in
        // Assert.assertEquals(checkOutPage.confirm(), "Checkout");
    }

    @Test(dataProvider = "addToCart", enabled = true, priority = 1)
    public void verifyCheckOutFunctionalityWithOutTermsButton(String productName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        ProductPage productPage = homePage.addToCartFromHome(productName);
        Assert.assertEquals(productPage.getConfirmationAddToCartMessage(), "The product has been added to your shopping cart");
        ShoppingCart shoppingCart = homePage.goToShoppingCart();
        Assert.assertEquals(shoppingCart.checkProductAdded(productName), productName);
        shoppingCart.checkOutWithoutTermsButton();
        Assert.assertEquals(shoppingCart.getErrorMessage(), "Please accept the terms of service before the next step.");

//System.out.println(shoppingCart.getErrorMessage());

    }

    @Test(dataProvider = "addToCart", enabled = true, priority = 1)
    public void verifyCheckOutWithEmptyShoppingCart(String productName) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        ShoppingCart shoppingCart = homePage.goToShoppingCart();
        Assert.assertEquals(shoppingCart.getEmptyCartMessage(), "Your Shopping Cart is empty!");


    }


    @DataProvider
    public Object[][] addToCart() throws IOException {

        DataDriven d = new DataDriven();
        List<List<String>> testData = d.getData("Add To Cart");
        // Ensure we remove the "TestCase" column from the extracted data
        Object[][] data = new Object[testData.size()][];
        for (int i = 0; i < testData.size(); i++) {
            // Skip the first column (TestCase Name)
            data[i] = testData.get(i).subList(1, testData.get(i).size()).toArray(new String[0]);
        }
        return data;
    }

    @DataProvider
    public Object[][] searchAndAddToCart() throws IOException {

        DataDriven d = new DataDriven();
        List<List<String>> testData = d.getData("searchAndAddToCart");
        // Ensure we remove the "TestCase" column from the extracted data
        Object[][] data = new Object[testData.size()][];
        for (int i = 0; i < testData.size(); i++) {
            // Skip the first column (TestCase Name)
            data[i] = testData.get(i).subList(1, testData.get(i).size()).toArray(new String[0]);
        }
        return data;
    }


}
