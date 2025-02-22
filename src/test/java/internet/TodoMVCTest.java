package internet;

import internet.pages.TodoMVCPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import supports.Browser;

import java.util.List;

public class TodoMVCTest {
    TodoMVCPage todoMVCPage;

    @BeforeMethod
    public void setUp() {
        Browser.openBrowser("chrome");
        todoMVCPage = new TodoMVCPage();
        todoMVCPage.open();
    }

    @Test
    public void ableCreateTodo() {
        todoMVCPage.addNewTodo("abc");
        todoMVCPage.addNewTodo("abc2");

        List<String> todoList = todoMVCPage.getTodoList();
        Assert.assertEquals(todoList.size(), 2);
    }

    @Test
    public void ableMarkCompleteTodo() {
        todoMVCPage.addNewTodo("abc");
        todoMVCPage.markComplete("abc");

        Assert.assertTrue(todoMVCPage.isComplete("abc"));
    }

    @AfterMethod
    public void tearDown() {
        Browser.quit();
    }
}