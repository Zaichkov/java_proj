package ru.intervale.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.intervale.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("Vasya", "Pupkin", "PupOK", "Intervale", "1234567890", "pup@gmail.com", "test1");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);

    }

}