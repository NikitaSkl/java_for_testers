package manager;

import model.Contact;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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
    public void removeContact(Contact contact){
        openHomePage();
        selectContact(contact);
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
    private void selectContact(Contact contact) {
        var checkbox=manager.driver.findElement(By.xpath(String.format("//input[@value='%s']",contact.id())));
        checkbox.click();
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

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        deleteContact();
    }

    private void selectAllContacts() {
        /*var checkboxes=manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes){
            checkbox.click();
        }*/
        click(By.cssSelector("input[id='MassCB']"));
    }

    public List<Contact> getList() {
        openHomePage();
        var contacts = new ArrayList<Contact>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs) {
            var checkbox=tr.findElement(By.name("selected[]"));
            var id=checkbox.getAttribute("value");
            var tds=tr.findElements(By.tagName("td"));
            var lastName = tds.get(1).getText();
            var firstName = tds.get(2).getText();
            var mobile = tds.get(5).getText();
            contacts.add(new Contact().withId(id).withFirstName(firstName).withLastName(lastName).withMobile(mobile));
        }
        return contacts;
    }
}
