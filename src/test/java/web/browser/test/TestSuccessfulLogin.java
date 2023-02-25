package web.browser.test;

import Base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TestSuccessfulLogin extends TestUtil {

    @Test (dataProvider = "correctUsers")
    public void successfulLogin(String userName, String password) throws InterruptedException{

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

        WebElement profileMenu = driver.findElement(By.id("react-burger-menu-btn"));
        WebElement profileMenuXBtn = driver.findElement(By.id("react-burger-cross-btn"));
        WebElement loginLink = driver.findElement(By.id("logout_sidebar_link"));
        WebElement productsPageTitle = driver.findElement(By.xpath("//span[text()='Products']"));

        profileMenu.click();
        //Thread.sleep(1200);
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait3.until(ExpectedConditions.visibilityOf(loginLink));

        Assert.assertTrue(loginLink.isDisplayed());
        profileMenuXBtn.click();
        Assert.assertTrue(productsPageTitle.isDisplayed());
        Assert.assertEquals(productsPageTitle.getText(), "PRODUCTS");
    }

    @DataProvider (name = "correctUsers")
    public static Object [][]readUsersFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctUsers.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[] [] csvDataObj = new Object[csvData.size()][2];
            for (int i = 0; i < csvData.size(); i++){
                csvDataObj[i] = csvData.get(i);
            }
            return csvDataObj;
        }catch (IOException e){
            System.out.println("Not possible to find CSV");
            return null;
        }
        catch (CsvException e){
            return null;
        }
    }
}
