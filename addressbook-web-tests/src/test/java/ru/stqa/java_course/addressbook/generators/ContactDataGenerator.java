package ru.stqa.java_course.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.java_course.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;
    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = genetateContacts(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for(ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                    contact.getAddress(), contact.getMobile(),contact.getHomePhone(), contact.getWorkPhone(),
                    contact.getSecondaryHomePhone(), contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
        }
        writer.close();
    }

    private List<ContactData> genetateContacts(int count) {
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
