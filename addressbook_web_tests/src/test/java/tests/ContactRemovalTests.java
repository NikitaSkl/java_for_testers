package tests;

import model.Contact;
import model.Group;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase{
    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()){
            app.contacts().createContact(new Contact("test firstname 1","test lastname 1","89876543211"));
        }
        app.contacts().removeContact();
    }
}
