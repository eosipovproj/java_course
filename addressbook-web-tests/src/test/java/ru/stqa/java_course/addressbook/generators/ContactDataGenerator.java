package ru.stqa.java_course.addressbook.generators;

import ru.stqa.java_course.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        List<ContactData> contacts = genetateContacts(count);
        save(contacts, file);

    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getAddress(), contact.getMobile(),contact.getHomePhone(), contact.getWorkPhone(),
                    contact.getSecondaryHomePhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
        }
        writer.close();
    }

    private static List<ContactData> genetateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstname(String.format("name %s", i)).withLastname(String.format("lastname %s", i))
                    .withAddress(String.format("address %s", i)).withMobile(String.format("mobile %s", i))
                    .withHomePhone(String.format("home phone %s", i)).withWorkPhone(String.format("work phone %s", i))
                    .withSecondaryHomePhone(String.format("phone2 %s", i)).withEmail(String.format("email1@email.ru %s", i))
                    .withEmail2(String.format("email2@email.ru %s", i)).withEmail3(String.format("email3@email.ru %s", i)));
        }
        return contacts;
    }
}
