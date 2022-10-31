package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public record Contact(int zipCode, String phone) implements Serializable {

    public static final Long serialVersionUID = 1L;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+79459349568");
        File tempFile = Files.createTempFile(null, null).toFile();

        /* Запись объекта во временный файл, который удалится системой */
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }

        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            if (Objects.equals(contact, contactFromFile)) {
                System.out.println(contactFromFile);
                System.out.println(contact);
            }
        }
    }
}
