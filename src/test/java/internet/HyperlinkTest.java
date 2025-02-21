package internet;

import internet.pages.HyperlinkPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import supports.Browser;

public class HyperlinkTest {

    @BeforeMethod
    public void setUp() {
        Browser.openBrowser("chrome");
    }

    @DataProvider
    Object[][] testHyperLink() {
        return new Object[][]{
                {"200", "https://the-internet.herokuapp.com/status_codes/200"},
                {"301", "https://the-internet.herokuapp.com/status_codes/301"},
                {"404", "https://the-internet.herokuapp.com/status_codes/404"},
                {"500", "https://the-internet.herokuapp.com/status_codes/500"}
        };
    }

    @Test(dataProvider = "testHyperLink")
    void linkTest(String linkText, String expectedUrl) {
        HyperlinkPage hyperlinkPage = new HyperlinkPage();
        hyperlinkPage.open();
        hyperlinkPage.click(linkText);
        Assert.assertEquals(hyperlinkPage.getCurrentUrl(), expectedUrl);
    }

    @Test
    void linkTest2() {
        HyperlinkPage hyperlinkPage = new HyperlinkPage();
        hyperlinkPage.open();
        hyperlinkPage.click("200");
        Assert.assertEquals(hyperlinkPage.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/200");
        Browser.back();

        hyperlinkPage.click("301");
        Assert.assertEquals(hyperlinkPage.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/301");
        hyperlinkPage.click("here");
        hyperlinkPage.click("404");
        Assert.assertEquals(hyperlinkPage.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/404");
        hyperlinkPage.click("here");
        hyperlinkPage.click("500");
        Assert.assertEquals(hyperlinkPage.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/500");
    }

    @AfterMethod
    public void tearDown() {
        Browser.quit();
    }

}
