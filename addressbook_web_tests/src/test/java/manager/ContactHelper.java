package manager;

import model.Contact;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }
    public void createContact(Contact contact){
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }
    public void removeContact(){
        openHomePage();
        selectContact();
        deleteContact();
    }
    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name(("selected[]")));
    }
    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(Contact contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("mobile"), contact.mobileNumber());
    }
    private void initContactCreation() {
            click(By.linkText("add new"));
    }
    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
    private void openHomePage() {
        if (!manager.isElementPresent(By.xpath("//input[@value='Delete']"))) { //проверка - не находимся ли уже на странице с контактами
            click(By.linkText("home"));
        }
    }
    private void closeAlert() {
        manager.driver.switchTo().alert().accept();
    }
}
