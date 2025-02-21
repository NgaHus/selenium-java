package internet;

import internet.pages.CheckboxPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import supports.Browser;

public class CheckboxesTest {
    @BeforeMethod
    public void setUp() {
        Browser.openBrowser("chrome");
    }

    @Test
    void testAbleSelectCheckboxes() {
        CheckboxPage checkboxPage = new CheckboxPage();
        checkboxPage.open();
        checkboxPage.check("1");
        checkboxPage.check("2");

        Assert.assertTrue(checkboxPage.isSelected("1"));
        Assert.assertTrue(checkboxPage.isSelected("2"));
    }

    @Test
    void testAbleUnSelectCheckboxes() {
        CheckboxPage checkboxPage = new CheckboxPage();
        checkboxPage.open();
        checkboxPage.uncheck("1");
        checkboxPage.uncheck("2");

        Assert.assertFalse(checkboxPage.isSelected("1"));
        Assert.assertFalse(checkboxPage.isSelected("2"));
    }

    @AfterMethod
    public void tearDown() {
        Browser.quit();
    }

}
