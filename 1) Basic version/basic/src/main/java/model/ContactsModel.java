package model;

import java.util.ArrayList;
import java.util.List;

public class ContactsModel {
    private List<Persona> contacts;
    private ContactDAO contactDAO;

    public ContactsModel() {
        this.contacts = new ArrayList<>();
        this.contactDAO = new ContactDAO();
        this.contacts = this.contactDAO.loadContacts();
    }

    public void addContact(Persona person) {
        this.contacts.add(person);
        this.contactDAO.saveContacts(this.contacts);
    }

    public void modifyContact(Persona person, int index) {
        this.contacts.set(index, person);
        this.contactDAO.saveContacts(this.contacts);

    }

    public void deleteContact(int index) {
        if (index >= 0 && index < this.contacts.size()) {
            this.contacts.remove(index);
        }
        this.contactDAO.saveContacts(this.contacts);

    }
    public List<Persona> getContacts() {
        return this.contacts;
    }

    @Override
    public String toString() {
        return "ContactsModel{" +
                "contacts=" + this.contacts +
                '}';
    }
}
