package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.example.Main.isValidPassword;

public class MainTest {

    @Test
    void validPassword(){
        Assert.assertTrue(isValidPassword("abA$12abc"));
    }

    @Test
    void checkEmptyPassword(){
        Assert.assertFalse(isValidPassword(null));
    }

    @Test
    void checkLength(){
        Assert.assertFalse(isValidPassword("abA$12c"));
    }

    @Test
    void checkMissingUpperCases(){
        Assert.assertFalse(isValidPassword("12345$abc"));
    }

    @Test
    void checkMissingLowerCases(){
        Assert.assertFalse(isValidPassword("12345$ABC"));
    }

    @Test
    void checkMissingNumber(){
        Assert.assertFalse(isValidPassword("abcAabcd$"));
    }

    @Test
    void checkMissingSpecialCharacters(){
        Assert.assertFalse(isValidPassword("abcAabc12"));
    }
    @Test
    void openBrowserWithDefaultMode(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }
}