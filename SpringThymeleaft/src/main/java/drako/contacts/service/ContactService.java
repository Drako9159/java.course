package drako.contacts.service;

import drako.contacts.model.Contact;
import drako.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> listContact() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findContactById(Integer idContact) {
        return contactRepository.findById(idContact).orElse(null);
    }

    @Override
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }
}
