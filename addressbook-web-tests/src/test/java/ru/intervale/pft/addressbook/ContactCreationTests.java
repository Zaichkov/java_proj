package ru.intervale.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        gotoAddContactPage();
        fillContactPage(new ContactData("Vasya", "Pupkin", "PupOK", "Intervale", "1234567890", "pup@gmail.com"));
        submitContactCreation();
        returnToHomePage();
    }

}