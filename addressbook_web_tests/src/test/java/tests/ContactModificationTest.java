package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase{
    @Test
    public void canModifyContact() {
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211"));
        }
        var oldContacts=app.contacts().getList();
        var rnd=new Random();
        var index=rnd.nextInt(oldContacts.size());
        var testData = new Contact().withFirstName("modified firstname");
        app.contacts().modifyContact(oldContacts.get(index),testData);
        var newContacts=app.contacts().getList();
        var expectedContacts=new ArrayList<>(oldContacts);
        Comparator<Contact> contactComparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(contactComparatorById);
        expectedContacts.set(index,testData.withId(oldContacts.get(index).id()));
        expectedContacts.sort(contactComparatorById);
        Assertions.assertEquals(expectedContacts,newContacts);
    }
}
