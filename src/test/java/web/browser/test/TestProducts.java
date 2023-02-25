package web.browser.test;

import Base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class TestProducts extends TestUtil {
    @Test
    public void addItemToTheCart(){
        LoginPage lgnPage = new LoginPage(driver);
        ProductPage productPage = lgnPage.login("standard_user", "secret_sauce");
        productPage.addItemToTheCart("backpack");
        productPage.addItemToTheCart("fleece-jacket");


        Assert.assertEquals(productPage.getItemsInTheCart(), 2);
    }
}
