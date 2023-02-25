package pages;

import Base.TestUtil;
import org.testng.annotations.Test;

public class LoginTestPOM extends TestUtil {

    @Test
    public void successfullLogin(){
        LoginPage LoginPage = new LoginPage(driver);
        ProductPage prPage = LoginPage.login("standard_user", "secret_sauce");
    }
}
