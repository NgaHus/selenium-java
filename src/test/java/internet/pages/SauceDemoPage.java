package internet.pages;

import org.openqa.selenium.By;
import supports.Browser;

import static supports.Browser.click;
import static supports.Browser.fill;

public class SauceDemoPage {
    public void open(){
        Browser.visit("https://www.saucedemo.com/");
    }

    public void login(String username, String password){
        fill(By.id("user-name"),username);
        fill(By.id("password"),password);
        click(By.cssSelector("input[type=submit]"));
    }

    public String getErrorMessage(){
        return Browser.getElement(By.xpath("//*[@data-test='error']")).getText();
    }
}
