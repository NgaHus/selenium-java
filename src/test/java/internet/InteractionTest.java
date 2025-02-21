package internet;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class InteractionTest {
    @Test
    void testHover(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/hovers");

        Actions actions = new Actions(driver);
        WebElement image1 = driver.findElement(By.xpath("//div[@class='example']/div[1]/img"));
        actions.moveToElement(image1).perform();

        String image1Profile = driver.findElement(By.xpath("//div[@class='example']/div[1]/div/h5")).getText();
        Assert.assertEquals(image1Profile,"name: user1");

        driver.quit();

    }

    @Test
    void testDragAndDrop(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        Actions actions = new Actions(driver);

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(),"B");

        actions.dragAndDrop(source,target).perform();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-b']/header")).getText(),"A");
        driver.quit();

    }
    @Test
    void horizontalSlider() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");

        Actions actions = new Actions(driver);

        WebElement pointer = driver.findElement(By.xpath("//div[@class='sliderContainer']/input"));
        int width = pointer.getSize().getWidth();

        actions.clickAndHold(pointer).moveByOffset(width,0).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        Assert.assertTrue(wait.until(ExpectedConditions.textToBe(By.id("range"),"5")));

        driver.quit();
    }

    @Test
    void scrollDown() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");

        Actions actions = new Actions(driver);

        for (int i = 0; i < 5; i++) {
            actions.scrollByAmount(0,500).perform();
            Thread.sleep(2000);
        }
        driver.quit();

    }

    @Test
    void rightClick(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/context_menu");

        Actions actions = new Actions(driver);
        actions.contextClick(driver.findElement(By.id("hot-spot"))).perform();
        driver.switchTo().alert().accept();
        driver.quit();
    }

    @Test
    void keyPress(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/key_presses");
        Actions actions = new Actions(driver);

        actions.keyDown(Keys.COMMAND).perform();
        System.out.println(driver.findElement(By.id("result")).getText());

        actions.keyDown("A").perform();
        System.out.println(driver.findElement(By.id("result")).getText());

        actions.keyDown(Keys.ENTER).perform();
        System.out.println(driver.findElement(By.id("result")).getText());
        driver.quit();

    }

    @Test
    void dynamicLoading(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.xpath("//button[.='Start']")).click();

//        Thread.sleep(5000);
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));// có thể nhìn thấy được

        Assert.assertEquals(driver.findElement(By.id("finish")).getText(),"Hello World!");
        driver.quit();
    }
    @Test
    void captureScreenShot(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/context_menu");

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(String.format("target/screenshot-%s-%s.png", "context-menu", System.currentTimeMillis()));
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
