package ru.intervale.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.intervale.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Vasya", "Pupkin", "PupOK", "Intervale", "1234567890", "pup@gmail.com", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().gotoHomePage();
    }

}
