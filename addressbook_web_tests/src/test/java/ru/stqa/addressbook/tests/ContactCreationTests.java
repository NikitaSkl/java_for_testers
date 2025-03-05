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
    public static List<Contact> singleRandomContact() {
        return List.of(new Contact().withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(15))
                .withMobile(CommonFunctions.randomString(20)));
    }
    public static List<Contact> negativeContactProvider() {
        var contacts = new ArrayList<Contact>(List.of(new Contact("", "test first name'", "", "","")));
        return contacts;
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateMultipleContacts(Contact contact) {
        var oldContacts = app.hbm().getContactsList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactsList();
        var expectedContacts = new ArrayList<>(oldContacts);
        Comparator<Contact> contactComparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(contactComparatorById);
        var maxId = newContacts.get(newContacts.size() - 1).id();
        expectedContacts.add(contact.withId(maxId));
        expectedContacts.sort(contactComparatorById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }
    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContactInGroup(Contact contact) {
        if (app.hbm().getGroupCount()==0){
            app.hbm().createGroup(new Group("", "test group name 1", "test group header 1", "test group footer 1"));
        }
        var firstGroup=app.hbm().getGroupList().get(0);
        var oldRelated=app.hbm().getContactsInGroup(firstGroup);
        app.contacts().createContact(contact, firstGroup);
        var newRelated=app.hbm().getContactsInGroup(firstGroup);
        Assertions.assertEquals(newRelated.size(), oldRelated.size()+1);
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(Contact contact) {
        //var oldContacts = app.contacts().getList();
        var oldContacts = app.hbm().getContactsList();
        app.contacts().createContact(contact);
        //var newContacts = app.contacts().getList();
        var newContacts = app.hbm().getContactsList();
        Assertions.assertEquals(newContacts, oldContacts);
    }
}
