package tests;

import model.Contact;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{
  @Test
  public void canCreateContact() {
    app.contacts().createContact(new Contact("test first name", "test middle name", "89876543211"));
  }
  @Test
  public void canCreateEmptyContact() {
    app.contacts().createContact(new Contact());
  }
}
