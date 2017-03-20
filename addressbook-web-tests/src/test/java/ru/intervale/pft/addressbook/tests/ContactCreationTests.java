package ru.intervale.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.intervale.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.gotoAddContactPage();
        app.fillContactPage(new ContactData("Vasya", "Pupkin", "PupOK", "Intervale", "1234567890", "pup@gmail.com"));
        app.submitContactCreation();
        app.returnToHomePage();
    }

}