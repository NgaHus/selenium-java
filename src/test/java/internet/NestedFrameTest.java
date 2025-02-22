package internet;

import internet.pages.NestedFramePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import supports.Browser;

public class NestedFrameTest {
    @BeforeMethod
    public void setUp() {
        Browser.openBrowser("chrome");
    }

    @Test
    void testNestedFrameContent() {
        NestedFramePage nestedFramePage = new NestedFramePage();
        nestedFramePage.open();
        nestedFramePage.switchToFrame("frame-top");
        nestedFramePage.switchToFrame("frame-left");

        Assert.assertTrue(nestedFramePage.getBodyText().contains("LEFT"));

        nestedFramePage.switchToFrame("parent");
        nestedFramePage.switchToFrame("frame-middle");

        Assert.assertTrue(nestedFramePage.getBodyText().contains("MIDDLE"));

        nestedFramePage.switchToFrame("parent");
        nestedFramePage.switchToFrame("frame-right");

        Assert.assertTrue(nestedFramePage.getBodyText().contains("RIGHT"));

        nestedFramePage.switchToFrame("default");
        nestedFramePage.switchToFrame("frame-bottom");

        Assert.assertTrue(nestedFramePage.getBodyText().contains("BOTTOM"));
    }

    @AfterMethod
    public void tearDown() {
        Browser.quit();
    }
}
