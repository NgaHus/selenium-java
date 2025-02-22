package supports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Browser {
    private static WebDriver driver;
    public static WebDriverWait wait;

    public static void openBrowser(String browser){
        switch (browser){
            case "chrome":{
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new");
                driver = new ChromeDriver(chromeOptions);
                break;
            }
            case "firefox": {
                driver = new FirefoxDriver();
                break;
            }
            case "safari": {
                driver = new SafariDriver();
                break;
            }
            case "edge":{
                driver = new EdgeDriver();
                break;
            }
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void visit(String url){
        driver.get(url);
    }

    public static void quit(){
        driver.quit();
    }

    public static String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public static String getTitle(){
        return driver.getTitle();
    }

    public static void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public static void fill(By locator,CharSequence... withText){
        driver.findElement(locator).sendKeys(withText);
    }

    public static String getText(By locator){
        return driver.findElement(locator).getText();
    }

    public static void captureScreen(String name){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(String.format("target/screenshot-%s-%s.png", name, System.currentTimeMillis()));
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebElement getElement(By locator){
        return driver.findElement(locator);
    }

    public static List<WebElement> getElements(By locator){
        return driver.findElements(locator);
    }

    public static boolean isSelected(By locator){
        return driver.findElement(locator).isSelected();
    }

    public static void back(){
        driver.navigate().back();
    }
    public static void acceptAlert(){
        driver.switchTo().alert().accept();
    }
    public static void dismissAlert(){
        driver.switchTo().alert().dismiss();
    }
    public static void sendKeyAlert(String keysToSend){
        driver.switchTo().alert().sendKeys(keysToSend);
    }

    public static void switchToFrame(String frameName){
        if (frameName.equals("parent")) {
            driver.switchTo().parentFrame();
        }else if (frameName.equals("default")) {
            driver.switchTo().defaultContent();
        }else {
            driver.switchTo().frame(frameName);
        }
    }

}