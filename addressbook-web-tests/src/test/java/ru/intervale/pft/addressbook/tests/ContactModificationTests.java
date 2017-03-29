package ru.intervale.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.intervale.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Vasya", "Pupkin", "PupOK", "Intervale", "1234567890", "pup@gmail.com", "test1"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactPage(new ContactData("aaa", "bbb", "PupOK", "Intervale", "1234567890", "pup@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();

    }
}
