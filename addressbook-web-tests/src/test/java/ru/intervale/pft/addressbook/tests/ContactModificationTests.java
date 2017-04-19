package ru.intervale.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.intervale.pft.addressbook.model.ContactData;
import ru.intervale.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withName("Vasya").withLastName("Pupkin").withNickName("PupOK").withCompany("Intervale")
                    .withMobilePhone("1234567890").withEmail("pup@gmail.com").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifyContact.getId()).withName("aaa").withLastName("111")
                .withNickName("PupOK").withCompany("Intervale").withMobilePhone("1234567890").withEmail("pup@gmail.com");
        app.contact().modify(contact);
        assertThat(app.contact().getContactCount(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));
    }


}
