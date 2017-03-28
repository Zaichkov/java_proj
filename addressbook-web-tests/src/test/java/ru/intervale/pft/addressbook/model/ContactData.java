package ru.intervale.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String nickName;
    private final String company;
    private final String mobilePhone;
    private final String email;
    private String group;

    public ContactData(String name, String lastName, String nickName, String company, String mobilePhone, String email, String group) {
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompany() {
        return company;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
