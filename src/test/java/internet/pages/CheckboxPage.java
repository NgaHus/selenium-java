package internet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import supports.Browser;

public class CheckboxPage {
    public void open() {
        Browser.visit("https://the-internet.herokuapp.com/checkboxes");
    }

    public void check(String checkboxNumber) {
        String locatorCheckbox = String.format("//form[@id='checkboxes']/input[%s]", checkboxNumber);
        WebElement checkboxElement = Browser.getElement(By.xpath(locatorCheckbox));
        if (!checkboxElement.isSelected()) {
            checkboxElement.click();
        }
    }

    public void uncheck(String checkboxNumber) {
        String locatorCheckbox = String.format("//form[@id='checkboxes']/input[%s]", checkboxNumber);
        WebElement checkboxElement = Browser.getElement(By.xpath(locatorCheckbox));
        if (checkboxElement.isSelected()) {
            checkboxElement.click();
        }
    }

    public boolean isSelected(String checkboxNumber) {
        String locatorCheckbox = String.format("//form[@id='checkboxes']/input[%s]", checkboxNumber);
        return Browser.isSelected(By.xpath(locatorCheckbox));
    }
}
