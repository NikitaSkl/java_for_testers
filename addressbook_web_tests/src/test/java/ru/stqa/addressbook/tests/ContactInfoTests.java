package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.Contact;

import java.util.stream.Collectors;
import java.util.stream.Stream;

//тесты сравнения информации о контакте в БД и пользовательском интерфейсе
public class ContactInfoTests extends TestBase{
    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new Contact("", CommonFunctions.randomString(8), CommonFunctions.randomString(10), CommonFunctions.randomStringOfNumbers(10), "src/test/resources/images/avatar.png", CommonFunctions.randomStringOfNumbers(9), CommonFunctions.randomStringOfNumbers(4), CommonFunctions.randomStringOfNumbers(8), "", "", "", ""));
        }
        var contacts = app.hbm().getContactsList();
        //в toMap передаем две функции - первая строит ключ, вторая значение
        var expectedPhones = contacts.stream().collect(Collectors.toMap(Contact::id, contact -> {
            return Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(i -> i != null && !i.equals(""))
                    .collect(Collectors.joining("\n"));
        }));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expectedPhones, phones);
    }
    @Test
    void testAddress(){
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new Contact("", CommonFunctions.randomString(8), CommonFunctions.randomString(10), "", "src/test/resources/images/avatar.png", "", "", "",  CommonFunctions.randomString(15), "", "", ""));
        }
        var contacts = app.hbm().getContactsList();
        var expectedAddress = contacts.stream()
                .collect(Collectors.toMap(Contact::id, Contact::address));
        var addresses=app.contacts().getAddresses();
        Assertions.assertEquals(expectedAddress,addresses);
    }
    @Test
    void testEmails(){
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new Contact("", CommonFunctions.randomString(8), CommonFunctions.randomString(10), "", "src/test/resources/images/avatar.png", "", "", "",  "", CommonFunctions.randomString(10), CommonFunctions.randomString(12), CommonFunctions.randomString(14)));
        }
        var contacts = app.hbm().getContactsList();
        var expectedEmails=contacts.stream().collect(Collectors.toMap(Contact::id, contact -> {
            return Stream.of(contact.email(),contact.email2(),contact.email3())
                    .filter(i -> i != null && !i.equals(""))
                    .collect(Collectors.joining("\n"));
        }));
        var emails=app.contacts().getEmails();
        Assertions.assertEquals(expectedEmails,emails);
    }
}

