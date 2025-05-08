package model;

import java.util.ArrayList;
import java.util.List;

public class ContactUserModel {
    private List<Persona> contacts;
    private ContactDAO contactDAO;

    public ContactUserModel(Utente user) {
        this.contacts = new ArrayList<>();
        this.contactDAO = new ContactDAO(user);
        this.contacts = this.contactDAO.selectAllContacts();
    }

    public void addContact(Persona person) {
        this.contacts.add(person);
        this.contactDAO.saveContactByUser(person);
    }

    public void modifyContact(Persona person, int index) {
        this.contacts.set(index, person);
        this.contactDAO.modifyContactByUser(person);

    }

    public void deleteContact(int index) {
        Persona person = this.contacts.remove(index);
        this.contactDAO.removeContactByUser(person);

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
