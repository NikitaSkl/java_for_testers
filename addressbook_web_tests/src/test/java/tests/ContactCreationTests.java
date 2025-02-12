package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {
    public static List<Contact> contactProvider() {
        var contacts = new ArrayList<Contact>();
        for (var firstName:
             List.of("test first name","")) {
            for (var lastName:
                 List.of("test middle name", "")) {
                for (var mobile:
                     List.of("89876543210", "")) {
                        contacts.add(new Contact(firstName, lastName, mobile));
                }
            }
        }
        for (int i = 1; i < 5; i++) {
            contacts.add(new Contact(randomString(i*3),randomString(i*3),randomStringOfNumbers(i*5)));
        }
        return contacts;
    }

    public static List<Contact> negativeContactProvider() {
        var contacts=new ArrayList<Contact>(List.of(new Contact("test first name'","","")));
        return contacts;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(Contact contact) {
        int contactsCount=app.contacts().getCount();
        app.contacts().createContact(contact);
        Assertions.assertEquals(contactsCount+1,app.contacts().getCount());
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(Contact contact) {
        int contactsCount=app.contacts().getCount();
        app.contacts().createContact(contact);
        Assertions.assertEquals(contactsCount,app.contacts().getCount());
    }
}
