package drako.contacts.service;

import drako.contacts.model.Contact;

import java.util.List;

public interface IContactService {

    public List<Contact> listContact();

    public Contact findContactById(Integer idContact);

    public void addContact(Contact contact);

    public void deleteContact(Contact contact);

}
