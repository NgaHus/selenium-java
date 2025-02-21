package internet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import supports.Browser;

public class FruitsPage {
    Select select;
    public FruitsPage() {
        select = new Select(Browser.getElement(By.id("fruits")));
    }

    public void open(){
        Browser.visit("https://output.jsbin.com/osebed/2");
    }

    public FruitsPage select(String option){
        select.selectByVisibleText(option);
        return this;
    }

    public FruitsPage deselect(String option){
        select.deselectByVisibleText(option);
        return this;
    }

    public void deselectAll(){
        select.deselectAll();
    }

    public boolean isSelected(String option){
        String locatorDropdown = String.format("//option[.='%s']", option);
        return Browser.isSelected(By.xpath(locatorDropdown));
    }

    public boolean isMultiple(){
        return select.isMultiple();
    }

}
