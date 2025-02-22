package internet.pages;

import org.openqa.selenium.By;
import supports.Browser;

import static supports.Browser.click;
import static supports.Browser.fill;

public class LoginPage {
    public void open(){
        Browser.visit("https://the-internet.herokuapp.com/login");
    }

    public void login(String username, String password){
        fill(By.id("username"),username);
        fill(By.id("password"),password);
        click(By.cssSelector("button[type=submit]"));
    }

    public String getMessage(String messageType){
        return Browser.getElement(By.className(messageType)).getText();
    }
}
