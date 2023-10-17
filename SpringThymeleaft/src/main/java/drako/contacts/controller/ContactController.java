package drako.contacts.controller;

import drako.contacts.model.Contact;
import drako.contacts.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String initialize(ModelMap modelMap) {
        List<Contact> contacts = contactService.listContact();
        contacts.forEach((contact) -> logger.info(contact.toString()));
        modelMap.put("contacts", contacts);
        return "index";
    }

    @GetMapping("/add")
    public String showAdd() {
        return "adder";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("contactForm") Contact contact) {
        logger.info("Contact to add: " + contact);
        contactService.addContact(contact);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable(value = "id") int idContact, ModelMap modelMap) {
        Contact contact = contactService.findContactById(idContact);
        logger.info("Contact to edit show: " + contact);
        modelMap.put("contact", contact);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("contact") Contact contact) {
        logger.info("Contact to edit: " + contact);
        contactService.addContact(contact);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int idContact) {
        Contact contact = new Contact();
        contact.setIdContact(idContact);
        contactService.deleteContact(contact);
        return "redirect:/";
    }

}
