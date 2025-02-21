package internet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NestedFrameTest {
    @Test
    void testNestedFrameContent(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        String content = driver.findElement(By.xpath("/html/body")).getText();
        Assert.assertTrue(content.contains("LEFT"));

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");
        String contentMiddle = driver.findElement(By.xpath("/html/body")).getText();

        Assert.assertTrue(contentMiddle.contains("MIDDLE"));

        driver.switchTo().parentFrame();// be FRAME-TOP
        driver.switchTo().frame("frame-right");
        content = driver.findElement(By.xpath("/html/body")).getText();
        Assert.assertTrue(content.contains("RIGHT"));

        driver.switchTo().defaultContent(); // default content
        driver.switchTo().frame("frame-bottom");
        content = driver.findElement(By.xpath("/html/body")).getText();
        Assert.assertTrue(content.contains("BOTTOM"));

        driver.quit();

    }
}
