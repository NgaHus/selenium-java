package internet;

import internet.pages.DropdownPage;
import internet.pages.FruitsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import supports.Browser;

public class DropdownTest {

    @BeforeMethod
    public void setUp() {
        Browser.openBrowser("chrome");
    }

    @Test
    void ableSelectOptions(){
        DropdownPage dropdownPage = new DropdownPage();
        dropdownPage.open();

        dropdownPage.select("1");
        Assert.assertTrue(dropdownPage.isSelected("1"));
    }

    @Test
    void ableSelectMultipleOptions(){
        FruitsPage fruitsPage = new FruitsPage();
        Assert.assertTrue(fruitsPage.isMultiple());

        fruitsPage
                .select("Banana")
                .select("Grape");

        Assert.assertTrue(fruitsPage.isSelected("Banana"));
        Assert.assertTrue(fruitsPage.isSelected("Grape"));
        Assert.assertFalse(fruitsPage.isSelected("Apple"));
        Assert.assertFalse(fruitsPage.isSelected("Orange"));

        fruitsPage.deselect("Banana");
        Assert.assertFalse(fruitsPage.isSelected("Banana"));
        Assert.assertTrue(fruitsPage.isSelected("Grape"));
        Assert.assertFalse(fruitsPage.isSelected("Apple"));
        Assert.assertFalse(fruitsPage.isSelected("Orange"));

        fruitsPage.deselectAll();
        Assert.assertFalse(fruitsPage.isSelected("Banana"));
        Assert.assertFalse(fruitsPage.isSelected("Grape"));
        Assert.assertFalse(fruitsPage.isSelected("Apple"));
        Assert.assertFalse(fruitsPage.isSelected("Orange"));
    }

    @AfterMethod
    public void tearDown() {
        Browser.quit();
    }
}
