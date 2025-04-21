package DemoWebShop.Tests;

import DemoWebShop.Data.DataDriven;
import DemoWebShop.PageObjects.*;
import DemoWebShop.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class EndToEnd extends BaseTest {


    @Test(dataProvider = "endToEnd", enabled = true, priority = 1)
    public void endToEnd(String gender, String firstName, String lastName, String email, String password,String productName,String searchInput,
    String country,String userCity, String userAddress, String zipCode, String phoneNumber) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //Registeration
        RegisterationPage registerationPage = homePage.goToRegisteration();
        registerationPage.registerApplication(gender, firstName, lastName, email, password);
        Assert.assertEquals(registerationPage.getID(), email);
        Assert.assertEquals(registerationPage.getConfirmationMessage(), "Your registration completed");
        registerationPage.logOut();
        //Login
        LoginPage loginPage = homePage.goToLogin();
        loginPage.loginApplication(email, password);
        Assert.assertEquals(loginPage.getID(), email);
        //Add product to cart from home page
        ProductPage productPage= homePage.addToCartFromHome(productName);
        Assert.assertEquals(productPage.getConfirmationAddToCartMessage(), "The product has been added to your shopping cart");
        //Search for a product and add it to cart
        homePage.searchAndSelect(searchInput,"heart");
        productPage.addToCart();
        Assert.assertEquals(productPage.getConfirmationAddToCartMessage(), "The product has been added to your shopping cart");
        //Confirm checkout and go to first checkout page
        ShoppingCart shoppingCart = homePage.goToShoppingCart();
        Assert.assertEquals(shoppingCart.checkProductAdded(productName), productName);
        shoppingCart.checkOut();
        //Complete Checkout
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.checkoutForm(firstName, lastName, country, email, userCity, userAddress, zipCode, phoneNumber);
        Assert.assertEquals(checkOutPage.getConfirmMessgae(), "Your order has been successfully processed!");
        String orderNumber = checkOutPage.getOrderNumber();
        OrdersPage ordersPage = homePage.goToOrders();
        ordersPage.selectOrder(orderNumber);
        Assert.assertEquals(ordersPage.getOrderNumberFromOrderPage(),orderNumber);
    }




    @DataProvider
    public Object[][] endToEnd() throws IOException {

        DataDriven d = new DataDriven();
        List<List<String>> testData = d.getData("End to end");
        // Ensure we remove the "TestCase" column from the extracted data
        Object[][] data = new Object[testData.size()][];
        for (int i = 0; i < testData.size(); i++) {
            // Skip the first column (TestCase Name)
            data[i] = testData.get(i).subList(1, testData.get(i).size()).toArray(new String[0]);
        }
        return data;
    }



}
