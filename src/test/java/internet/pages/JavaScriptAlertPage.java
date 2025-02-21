package internet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import supports.Browser;

public class JavaScriptAlertPage {
    public void open() {
        Browser.visit("https://the-internet.herokuapp.com/javascript_alerts");
    }

    public void clickButton(String buttonName) {
        String locatorButton= String.format("//button[.='%s']", buttonName);
        Browser.getElement(By.xpath(locatorButton)).click();
    }
    public void acceptAlert() {
        Browser.acceptAlert();
    }
    public void dismissAlert() {
        Browser.dismissAlert();
    }

    public String getResult() {
        return Browser.getElement(By.id("result")).getText();
    }

    public void sendKeyAlert(String text) {
        Browser.sendKeyAlert(text);
    }

}
