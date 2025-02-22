package internet;

import internet.pages.SauceDemoPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import supports.Browser;

public class DemoLoginTest {
    SauceDemoPage sauceDemoPage;

    @BeforeMethod
    public void setUp() {
        Browser.openBrowser("chrome");
        sauceDemoPage = new SauceDemoPage();
        sauceDemoPage.open();
    }

    @DataProvider
    Object[][] testSuccessData(){
        return new Object[][]{
                {"standard_user","secret_sauce","https://www.saucedemo.com/inventory.html"}
        };
    }

    @Test(dataProvider = "testSuccessData")
    void loginSuccessTest(String username,String password,String expectedUrl) {
        sauceDemoPage.login(username, password);

        Assert.assertEquals(Browser.getCurrentUrl(),expectedUrl);
        Assert.assertEquals(Browser.getTitle(),"Swag Labs");
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
        sauceDemoPage.login(username, password);

        Assert.assertEquals(Browser.getCurrentUrl(),expectedUrl);
        Assert.assertEquals(sauceDemoPage.getErrorMessage(), expectedMessage);
    }

    @AfterMethod
    public void tearDown() {
        Browser.quit();
    }
}
