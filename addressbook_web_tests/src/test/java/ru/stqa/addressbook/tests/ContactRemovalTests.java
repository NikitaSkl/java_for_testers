package ru.stqa.addressbook.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase{
    public static List<Contact> singleRandomContact() {
        return List.of(new Contact().withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(15))
                .withMobile(CommonFunctions.randomString(20)));
    }
    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount()==0){
            app.hbm().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211","src/test/resources/images/avatar.png", "", "", "", "", "", "", ""));
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
            app.hbm().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211","src/test/resources/images/avatar.png", "", "", "", "", "", "", ""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.hbm().getContactCount());
    }
    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canRemoveContactFromGroup(Contact contact){
        if (app.hbm().getGroupCount()==0){
            app.hbm().createGroup(new Group("", "test group name 1", "test group header 1", "test group footer 1"));
        }
        var firstGroup=app.hbm().getGroupList().get(0);
        var oldRelatedBeforeRemoval=app.hbm().getContactsInGroup(firstGroup);
        app.contacts().createContact(contact, firstGroup);
        var newRelatedBeforeRemoval=app.hbm().getContactsInGroup(firstGroup);
        Assertions.assertEquals(newRelatedBeforeRemoval.size(), oldRelatedBeforeRemoval.size()+1);

        var rnd=new Random();
        var index=rnd.nextInt(newRelatedBeforeRemoval.size());
        app.contacts().removeContactFromGroup(newRelatedBeforeRemoval.get(index), firstGroup);
        var newRelatedAfterRemoval=app.hbm().getContactsInGroup(firstGroup);
        Assertions.assertEquals(newRelatedAfterRemoval.size(), newRelatedBeforeRemoval.size()-1);
    }
}
