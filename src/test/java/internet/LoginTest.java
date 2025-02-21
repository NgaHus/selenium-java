package internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest
{
    @DataProvider
    Object[][] testData(){
        return new Object[][]{
                {"tomsmith","SuperSecretPassword!","https://the-internet.herokuapp.com/secure","success","You logged into a secure area!"},
                {"","","https://the-internet.herokuapp.com/login","error","Your username is invalid!"},
                {"tomsmih","SuperSecretPassword!","https://the-internet.herokuapp.com/login","error","Your username is invalid!"},
                {"tomsmith","","https://the-internet.herokuapp.com/login","error","Your password is invalid!"},
                {"tomsmith","SuperSecretPassword","https://the-internet.herokuapp.com/login","error","Your password is invalid!"},
        };
    }

    @Test(dataProvider = "testData")
    void authenticationFormTest(String username,String password,String expectedUrl,String expectedMessageType,String expectedMessageContent) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type=submit]")).click();

        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
        Assert.assertTrue(driver.findElement(By.className(expectedMessageType)).getText().contains(expectedMessageContent));
        driver.quit();
    }
}
