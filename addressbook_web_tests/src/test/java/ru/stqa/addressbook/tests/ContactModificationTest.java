package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.Group;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase{
    @Test
    public void canModifyContact() {
        if (app.hbm().getContactCount()==0){
            app.hbm().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211","src/test/resources/images/avatar.png"));
        }
        var oldContacts=app.hbm().getContactsList();
        var rnd=new Random();
        var index=rnd.nextInt(oldContacts.size());
        var testData = new Contact().withFirstName("modified firstname");
        app.contacts().modifyContact(oldContacts.get(index),testData);
        var newContacts=app.hbm().getContactsList();
        var expectedContacts=new ArrayList<>(oldContacts);
        Comparator<Contact> contactComparatorById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(contactComparatorById);
        expectedContacts.set(index,testData.withId(oldContacts.get(index).id()));
        expectedContacts.sort(contactComparatorById);
        Assertions.assertEquals(expectedContacts,newContacts);
    }
    @Test
    public void canAddContactToGroup() {
        var contactAlreadyInGroup=false;
        if (app.hbm().getContactCount()==0){
            app.hbm().createContact(new Contact("", "test firstname 1", "test lastname 1", "89876543211","src/test/resources/images/avatar.png"));
        }
        if (app.hbm().getGroupCount()==0){
            app.hbm().createGroup(new Group("", "test group name 1", "test group header 1", "test group footer 1"));
        }

        var contacts=app.hbm().getContactsList();
        var groups=app.hbm().getGroupList();
        var groupIndex = new Random().nextInt(groups.size());
        var newGroup=app.hbm().getGroupList().get(groupIndex);
        var oldRelated=app.hbm().getContactsInGroup(newGroup);

        var contactIndex = new Random().nextInt(contacts.size());
        var contactId=contacts.get(contactIndex).id();
        for (Contact contact: oldRelated) {
            if (contact.id().equals(contactId)) {
                contactAlreadyInGroup=true;
                break;
            }
        }

        app.contacts().addContactToGroup(contacts.get(contactIndex),newGroup);
        var newRelated=app.hbm().getContactsInGroup(newGroup);
        if (contactAlreadyInGroup) {
            Assertions.assertEquals(newRelated.size(),oldRelated.size());
        }
        else Assertions.assertEquals(newRelated.size(),oldRelated.size()+1);

    }
}
