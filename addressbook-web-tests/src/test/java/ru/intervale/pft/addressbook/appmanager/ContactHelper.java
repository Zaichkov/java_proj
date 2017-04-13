package ru.intervale.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.intervale.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();

    }

    public void deleteSelectedContacts() {
        click(By.xpath(".//*[@value='Delete']"));
        alertAccept();
    }

    public void initContactModification(int index) {
        wd.findElements(By.cssSelector("img[alt='Edit']")).get(index).click();
        }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        gotoAddContactPage();
        fillContactPage(contact, true);
        submitContactCreation();
        app.goTo().gotoHomePage();
    }
    public void modify(int index, ContactData contact) {
        initContactModification(index);
        fillContactPage(contact, false);
        submitContactModification();
        app.goTo().gotoHomePage();
    }
    public void delete(int index) {
        selectContact(index);
        deleteSelectedContacts();
        app.goTo().gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elementsTr = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement elementTr : elementsTr) {
            List<WebElement> elementsTd = elementTr.findElements(By.cssSelector("td"));
            contacts.add(new ContactData(Integer.parseInt(elementsTd.get(0).findElement(By.tagName("input")).getAttribute("id")),
                    elementsTd.get(2).getText(), elementsTd.get(1).getText(), null, null, null, null, null));
        }
        return contacts;
    }

}
