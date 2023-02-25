package web.browser.test;

import Base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.CsvHelper;

import java.io.IOException;

public class CheckoutTest extends TestUtil {
    @DataProvider(name = "correctUsers")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/correctUsers.csv");
    }

    @Test(dataProvider = "correctUsers")
    public void TestSuccessfulLogin(String userName, String password){

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);

        productPage.addItemToTheCart("fleece-jacket");
        productPage.enterIntoCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutInformationPage.fillCheckoutInfo("Dobroslava", "Angelova", "1000");

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.finishCheckout();

        CompletedOrderPage completedOrderPage = new CompletedOrderPage(driver);
        Assert.assertTrue(completedOrderPage.getCheckoutHeader());


    }
}
