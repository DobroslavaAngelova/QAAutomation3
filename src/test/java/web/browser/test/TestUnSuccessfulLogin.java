package web.browser.test;

import Base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TestUnSuccessfulLogin extends TestUtil {
    @Test (dataProvider = "wrongUsers")
    public void unsuccessfulLogin(String userName, String password){

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        //Actions
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(userName);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginBtn.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertEquals(errorMessage.getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @DataProvider(name = "wrongUsers")
    public Object [][]getUsers(){
        return new Object[][]{
                {"standard_user","wrong"},
                {"wrong","secret_sauce"},
                {"wrong","wrong"}
        };
    }
  }
