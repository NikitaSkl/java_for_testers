package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase{
    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount()==0){
            app.hbm().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211","src/test/resources/images/avatar.png"));
        }
        var oldContacts=app.hbm().getContactsList();
        var rnd=new Random();
        var index=rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts=app.hbm().getContactsList();
        var expectedContacts=new ArrayList<>(oldContacts);
        expectedContacts.remove(index);
        Assertions.assertEquals(expectedContacts,newContacts);
    }
    @Test
    public void canRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount()==0){
            app.hbm().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211","src/test/resources/images/avatar.png"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.hbm().getContactCount());
    }
}
