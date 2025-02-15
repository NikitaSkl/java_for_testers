package tests;

import model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
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
                        contacts.add(new Contact().withFirstName(firstName).withLastName(lastName).withMobile(mobile));
                }
            }
        }
        for (int i = 1; i < 5; i++) {
            contacts.add(new Contact().withFirstName(randomString(i*3)).withLastName(randomString(i*3)).withMobile(randomStringOfNumbers(i*5)));
        }
        return contacts;
    }

    public static List<Contact> negativeContactProvider() {
        var contacts=new ArrayList<Contact>(List.of(new Contact("", "test first name'", "", "")));
        return contacts;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(Contact contact) {
        var oldContacts=app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts=app.contacts().getList();
        var expectedContacts=new ArrayList<>(oldContacts);
        Comparator<Contact> contactComparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(contactComparatorById);
        expectedContacts.add(contact.withId(newContacts.get(newContacts.size()-1).id()));
        expectedContacts.sort(contactComparatorById);
        Assertions.assertEquals(expectedContacts,newContacts);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(Contact contact) {
        var oldContacts=app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts=app.contacts().getList();
        Assertions.assertEquals(newContacts,oldContacts);
    }
}
