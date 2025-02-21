package internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoLoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.saucedemo.com/");
    }

    @DataProvider
    Object[][] testSuccessData(){
        return new Object[][]{
                {"standard_user","secret_sauce","https://www.saucedemo.com/inventory.html"}
        };
    }

    @Test(dataProvider = "testSuccessData")
    void loginSuccessTest(String username,String password,String expectedUrl) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type=submit]")).click();

        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
        Assert.assertEquals(driver.getTitle(),"Swag Labs");
    }

    @DataProvider
    Object[][] testFailData(){
        return new Object[][]{
                // Check empty username
                {"","","https://www.saucedemo.com/","Epic sadface: Username is required"},
                // Check empty password
                {"standard_user","","https://www.saucedemo.com/","Epic sadface: Password is required"},
                // Check invalid username
                {"invalid_user","secret_sauce","https://www.saucedemo.com/","Epic sadface: Username and password do not match any user in this service"},
                // Check invalid password
                {"standard_user","invalid_password","https://www.saucedemo.com/","Epic sadface: Username and password do not match any user in this service"},
                // Check locked out user
                {"locked_out_user","secret_sauce","https://www.saucedemo.com/","Epic sadface: Sorry, this user has been locked out."},
        };
    }

    @Test(dataProvider = "testFailData")
    void loginFailTest(String username,String password,String expectedUrl,String expectedMessage) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type=submit]")).click();

        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@data-test='error']")).getText(), expectedMessage);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
