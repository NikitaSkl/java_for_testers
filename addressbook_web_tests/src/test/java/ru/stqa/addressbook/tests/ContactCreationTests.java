package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.Group;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
    public static List<Contact> contactProvider() throws IOException {
        var contacts = new ArrayList<Contact>();
        for (var firstName : List.of("test first name", "")) {
            for (var lastName : List.of("test middle name", "")) {
                for (var mobile : List.of("89876543210", "")) {
                    contacts.add(new Contact().withFirstName(firstName).withLastName(lastName).withMobile(mobile).withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
                }
            }
        }
        var xml= Files.readString(Path.of("contacts.xml"));;
        var mapper=new XmlMapper();
        var value = mapper.readValue(xml, new TypeReference<List<Contact>>() {}); //TypeRef - класс без реализации, только с декларацией - в {} его реализация (пустой)
        contacts.addAll(value);
        return contacts;
    }

    public static List<Contact> negativeContactProvider() {
        var contacts = new ArrayList<Contact>(List.of(new Contact("", "test first name'", "", "","")));
        return contacts;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(Contact contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        var expectedContacts = new ArrayList<>(oldContacts);
        Comparator<Contact> contactComparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(contactComparatorById);
        expectedContacts.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withPhoto(""));
        expectedContacts.sort(contactComparatorById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(Contact contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
    }
}
