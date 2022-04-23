package com.example.contactsapplication;

public class Contact {
    private String name;
    private String contactno;

    public Contact(String name, String contactno) {
        this.name = name;
        this.contactno = contactno;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", contactno='" + contactno + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }
}
