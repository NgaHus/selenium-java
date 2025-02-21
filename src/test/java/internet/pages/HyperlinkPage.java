package internet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import supports.Browser;

public class HyperlinkPage {

    public void open() {
        Browser.visit("https://the-internet.herokuapp.com/status_codes");
    }

    public void click(String linkText) {
        Browser.getElement(By.linkText(linkText)).click();
    }

    public String getCurrentUrl(){
        return Browser.getDriver().getCurrentUrl();
    }
}
