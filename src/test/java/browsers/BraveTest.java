package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverFinder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class BraveTest {
    @Test
    void openBrowserWithDefaultMode(){
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Brave Browser.app/Contents/MacOS/Brave Browser");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev/");
        Assert.assertEquals(driver.getTitle(),"Selenium");
        driver.quit();
    }

//    private File getChromeLocation() {
//        ChromeOptions options = new ChromeOptions();
//        options.setBrowserVersion("stable");
//        DriverFinder finder = new DriverFinder();
//        return new File(finder());
//    }
}
