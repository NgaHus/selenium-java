package internet.pages;

import org.openqa.selenium.By;
import supports.Browser;

public class NestedFramePage {

    public void open(){
        Browser.visit("https://the-internet.herokuapp.com/nested_frames");
    }

    public void switchToFrame(String frameName){
        Browser.switchToFrame(frameName);
    }

    public String getBodyText(){
        return Browser.getElement(By.xpath("/html/body")).getText();
    }

}
