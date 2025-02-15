package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactRemovalTests extends TestBase{
    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211"));
        }
        var oldContacts=app.contacts().getList();
        var rnd=new Random();
        var index=rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts=app.contacts().getList();
        var expectedContacts=new ArrayList<>(oldContacts);
        expectedContacts.remove(index);
        Assertions.assertEquals(expectedContacts,newContacts);
    }
    @Test
    public void canRemoveAllContactsAtOnce() {
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getCount());
    }
}
