package internet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import supports.Browser;

import java.util.List;

import static java.awt.SystemColor.text;
import static supports.Browser.click;
import static supports.Browser.fill;

public class TodoMVCPage {
    public void open(){
        Browser.visit("https://todomvc.com/examples/react/dist/");
    }

    public void addNewTodo(String textTodo){
        fill(By.id("todo-input"),textTodo + Keys.ENTER);
    }

    public void markComplete(String textTodo){
        String inputTodoLocator = String.format("//label[text()='%s']/preceding-sibling::input", textTodo);
        Browser.getElement(By.xpath(inputTodoLocator)).click();
    }

    public boolean isComplete(String textTodo){
        WebElement todoElement = getTodoByName(textTodo);
        return  todoElement.getAttribute("class").equals("completed");
    }


    public List<String> getTodoList(){
        List<String> todoList = Browser.getElements(By.xpath("//*[@data-testid='todo-list']/li"))
                .stream()
                .map(cell -> cell.getText())
                .toList();

        return todoList;
    }
    public WebElement getTodoByName(String todoName){
        return  Browser.getElements(By.xpath("//*[@data-testid='todo-list']/li"))
                .stream()
                .filter(t -> t.getText().equals(todoName))
                .findFirst().get();
    }
}
