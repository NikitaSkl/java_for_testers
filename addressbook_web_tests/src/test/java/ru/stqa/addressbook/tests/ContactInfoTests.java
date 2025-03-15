package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.Contact;

import java.util.stream.Collectors;
import java.util.stream.Stream;

//тесты сравнения информации о контакте в БД и пользовательском интерфейсе
public class ContactInfoTests extends TestBase{
    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211", "src/test/resources/images/avatar.png", "", "", ""));
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
}

