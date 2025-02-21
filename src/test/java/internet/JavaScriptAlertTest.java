package internet;

import internet.pages.JavaScriptAlertPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import supports.Browser;

public class JavaScriptAlertTest {
    JavaScriptAlertPage javaScriptAlertPage;

    @BeforeMethod
    public void setUp() {
        Browser.openBrowser("chrome");
        javaScriptAlertPage = new JavaScriptAlertPage();
        javaScriptAlertPage.open();
    }

    @Test
    public void testAbleClickJSAlert() {
        javaScriptAlertPage.clickButton("Click for JS Alert");
        javaScriptAlertPage.acceptAlert();

        Assert.assertEquals(javaScriptAlertPage.getResult(), "You successfully clicked an alert");
    }

    @Test
    void ableCancelJSConfirm() {
        javaScriptAlertPage.clickButton("Click for JS Confirm");
        javaScriptAlertPage.dismissAlert();

        Assert.assertEquals(javaScriptAlertPage.getResult(), "You clicked: Cancel");
    }

    @Test
    void ableAcceptJSConfirm() {
        javaScriptAlertPage.clickButton("Click for JS Confirm");
        javaScriptAlertPage.acceptAlert();

        Assert.assertEquals(javaScriptAlertPage.getResult(), "You clicked: Ok");
    }

    @Test
    void ableSendKeysInJSPrompt() {
        javaScriptAlertPage.clickButton("Click for JS Prompt");
        javaScriptAlertPage.sendKeyAlert("hello");
        javaScriptAlertPage.acceptAlert();

        Assert.assertEquals(javaScriptAlertPage.getResult(), "You entered: hello");
    }

    @AfterMethod
    public void tearDown() {
        Browser.quit();
    }
}
