package ru.intervale.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.intervale.pft.addressbook.model.ContactData;
import ru.intervale.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager app) {
        super(app);
    }


    public void gotoAddContactPage() {
        click(By.linkText("add new"));
    }
    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactPage(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address"), contactData.getAddress());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }

    public void deleteSelectedContacts() {
        click(By.xpath(".//*[@value='Delete']"));
        alertAccept();
    }

    public void initContactModification(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
        }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        gotoAddContactPage();
        fillContactPage(contact, true);
        submitContactCreation();
        contactCache = null;
        app.goTo().gotoHomePage();
    }
    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactPage(contact, false);
        submitContactModification();
        contactCache = null;
        app.goTo().gotoHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
        app.goTo().gotoHomePage();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elementsTr = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement elementTr : elementsTr) {
            List<WebElement> elementsTd = elementTr.findElements(By.cssSelector("td"));
            String allPhones = elementsTd.get(5).getText();
            String allEmails = elementsTd.get(4).getText();
            contactCache.add(new ContactData().withId(Integer.parseInt(elementsTd.get(0).findElement(By.tagName("input"))
                    .getAttribute("id"))).withName(elementsTd.get(2).getText()).withLastName(elementsTd.get(1).getText())
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(elementsTd.get(3).getText()));
        }
        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModification(contact.getId());
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId((contact.getId())).withName(firstName).withLastName(lastName).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withHomePhone(homePhone).withMobilePhone(mobilePhone)
                .withWorkPhone(workPhone);
    }
}
