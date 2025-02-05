package manager;

import org.openqa.selenium.By;

//Помощник по работе с сессиями (логин, логаут)
public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager manager){

        super(manager);
    }
    void login(String username, String password) {
        type(By.name("user"),username);
        type(By.name("pass"),password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}
