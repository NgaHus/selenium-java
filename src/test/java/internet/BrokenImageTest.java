package internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrokenImageTest {

    @Test
    void verifyBrokenImage(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/broken_images");
        driver.findElements(By.xpath("//div/img")).forEach( img ->{
            int naturalHeight = Integer.parseInt( img.getDomProperty("naturalHeight"));
            int naturalWidth = Integer.parseInt( img.getDomProperty("naturalWidth"));
            if (naturalHeight==0 && naturalWidth==0){
                System.out.println("Image is broken");
            }else {
                System.out.println("Image is loaded");
            }
        });
        driver.quit();
    }

}
