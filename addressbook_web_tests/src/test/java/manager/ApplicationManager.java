package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//класс с методами для управления приложением, для взаимодействия с ним
public class ApplicationManager {

    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;

    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(2064, 1128));
            session().login("admin", "secret");
        }
    }

    public LoginHelper session(){
        if (session == null){
            session=new LoginHelper(this); //при вызове конструктора помощника сообщаем,кто его менеджер
        }
        return session;
    }
    public GroupHelper groups(){
        if (groups == null){
            groups=new GroupHelper(this);
        }
        return groups;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

}
