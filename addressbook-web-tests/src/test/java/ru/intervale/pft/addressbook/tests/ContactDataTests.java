package ru.intervale.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.intervale.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDataTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withName("Vasya").withLastName("Pupkin").withNickName("PupOK")
                    .withCompany("Intervale").withMobilePhone("123-4567-890").withEmail("pup@gmail.com").withGroup("test1")
            .withAddress("street").withEmail2("pup2@gmail.com").withEmail3("pup3@gmail.com").withHomePhone("(123)444").withWorkPhone("66 77 88"));
        }
    }

    @Test
    public void testContactData() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals("")).map(ContactDataTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }


    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }


}
