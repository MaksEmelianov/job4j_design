package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ContactMain {

    public static void main(String[] args) {
        final Person person = new Person(false, 30,
                new Contact("23434232"), new String[]{"Worked", "Married"});

        /* Преобразуем объект person в json-строку.*/
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку*/
        final String personJson = "{"
                + "\"sex\":true,"
                + "\"age\":33,"
                + "\"contact\":"
                + "{"
                + "\"phone\":\"22-222\""
                + "},"
                + "\"statuses\":"
                + "[\"Student\",\"Free\"]"
                + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
