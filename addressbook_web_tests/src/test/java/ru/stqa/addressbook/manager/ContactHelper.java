package ru.stqa.addressbook.manager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.Contact;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void modifyContact(Contact oldContact, Contact newContact) {
        openHomePage();
        initContactModification(oldContact);
        fillContactForm(newContact);
        submitContactModification();
        returnToHomePage();
    }
    public void createContact(Contact contact, Group group){
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }
    public void removeContactFromGroup(Contact contact, Group group) {
        openHomePage();
        selectGroupFromList(group);
        selectContact(contact);
        submitContactRemoval();
    }
    public void addContactToGroup(Contact contact, Group firstGroup) {
        openHomePage();
        selectContact(contact);
        selectGroupToAdd(firstGroup);
        submitContactAddition();
    }

    private void selectGroupToAdd(Group firstGroup) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(firstGroup.id());
    }

    private void submitContactAddition() {
        click(By.name("add"));
    }

    private void selectGroupFromList(Group group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void submitContactRemoval() {
        click(By.name("remove"));
    }

    private void selectGroup(Group group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
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
        type(By.name("mobile"), contact.mobile());
        if (!("".equals(contact.photo()))) {
            attach(By.name("photo"), contact.photo());
        }
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
        /*if (!manager.isElementPresent(By.xpath("//input[@value='Delete']"))) { //проверка - не находимся ли уже на странице с контактами
            click(By.linkText("home"));
        }*/
        click(By.xpath("//img[@id='logo']"));
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

    private void submitContactModification() {
        click(By.cssSelector("input[value='Update']"));
    }

    private void initContactModification(Contact oldContact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']",oldContact.id())));
    }

    public String getPhonesOfContact(Contact contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]",contact.id()))).getText();
    }

    public Map<String,String> getPhones() {
        var result=new HashMap<String,String>();
        var tableRows=manager.driver.findElements(By.name("entry"));
        for (WebElement tableRow : tableRows) {
            var id=tableRow.findElement(By.tagName("input")).getAttribute("value");
            var phones=tableRow.findElements(By.tagName("td")).get(5).getText();
            result.put(id,phones);
        }
        return result;
    }

    public Map<String, String> getAddresses() {
        var result=new HashMap<String,String>();
        var tableRows=manager.driver.findElements(By.name("entry"));
        for (WebElement tableRow : tableRows) {
            var id=tableRow.findElement(By.tagName("input")).getAttribute("value");
            var address=tableRow.findElements(By.tagName("td")).get(3).getText();
            result.put(id,address);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result=new HashMap<String, String>();
        var tableRows=manager.driver.findElements(By.name("entry"));
        for (WebElement tableRow : tableRows) {
            var id=tableRow.findElement(By.tagName("input")).getAttribute("value");
            var emails=tableRow.findElements(By.tagName("td")).get(4).getText();
            result.put(id,emails);
        }
        return result;
    }
}
