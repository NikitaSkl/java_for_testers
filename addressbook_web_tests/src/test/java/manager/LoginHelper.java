package manager;

import org.openqa.selenium.By;

//Помощник по работе с сессиями (логин, логаут)
public class LoginHelper {
    private final ApplicationManager manager;

    public LoginHelper(ApplicationManager manager){

        this.manager = manager;
    }
    void login(String username, String password) {
        manager.driver.findElement(By.name("user")).sendKeys(username);
        manager.driver.findElement(By.name("pass")).click();
        manager.driver.findElement(By.name("pass")).sendKeys(password);
        manager.driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }
}
