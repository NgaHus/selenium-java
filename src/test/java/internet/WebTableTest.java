package internet;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WebTableTest {
    @Test
    void webTableTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");

        // XPATH=//table[@id='table1']/tbody/tr[1]/td[1] = cell[1][1]
        // XPATH=//table[@id='table1']/tbody/tr[1]/td = row 1
        // XPATH=//table[@id='table1']/tbody/tr/td[1] = column1

        List<Double> dues =
        driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(cell -> Double.parseDouble(cell.getText().replace("$", "")))
                .toList();
        dues.forEach(System.out::println);

        Double maxDueValue = dues.stream().max(Comparator.naturalOrder()).get();
        int maxDueIndex = dues.indexOf(maxDueValue);

        String lastName = driver.findElement(By.xpath(String.format("//table[@id='table1']/tbody/tr[%d]/td[1]", maxDueIndex + 1))).getText();
        String firstName = driver.findElement(By.xpath(String.format("//table[@id='table1']/tbody/tr[%d]/td[2]", maxDueIndex + 1))).getText();

        Assert.assertEquals(String.format("%s %s", lastName, firstName), "Doe Jason");

        driver.quit();

    }

    @Test
    void webTableTest2(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");

        List<Person> personList = new ArrayList<>();
        driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).forEach(
                row -> {
                    String[] rowContent = row.getText().split(" ");
                    personList.add(new Person(rowContent[0], rowContent[1], rowContent[3]));
                }
        );
        Double minDueValue = personList.stream()
                .min(Comparator.comparing(Person::getDue))
                .get()
                .getDue();
        List<String> maxDuePersonList = personList.stream()
                        .filter(p -> Objects.equals(p.getDue(), minDueValue))
                                .map(Person::getName)
                                        .toList();
        Assert.assertEquals(maxDuePersonList, List.of("Smith John", "Conway Tim"));
        driver.quit();

    }
}
