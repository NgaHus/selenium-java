package internet;

import internet.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import supports.Browser;

public class LoginTest
{
    @BeforeMethod
    public void setUp() {
        Browser.openBrowser("chrome");
    }

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
        LoginPage  loginPage = new LoginPage();
        loginPage.open();
        loginPage.login(username, password);

        Assert.assertEquals(Browser.getCurrentUrl(),expectedUrl);
        Assert.assertTrue(loginPage.getMessage(expectedMessageType).contains(expectedMessageContent));
    }

    @AfterMethod
    public void tearDown() {
        Browser.quit();
    }
}
