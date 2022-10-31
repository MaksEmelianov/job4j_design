package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AutoMain {

    public static void main(String[] args) {
        final Auto auto = new Auto(true, 6, "A123AA123RUS",
                new Parameter(1990, 6500), new String[]{"Aleksey", "Dmitriy"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(auto));

        final String autoJson = "{"
                + "\"shipping\":true,"
                + "\"countWheels\":8,"
                + "\"number\":"
                + "\"B555BB12RUS\","
                + "\"parameter\":"
                + "{"
                + "\"yearRelease\":2020,"
                + "\"engineCapacity\":3400"
                + "},"
                + "\"drivers\":"
                + "[\"Petr\",\"Mihail\"]"
                + "}";

        final Auto autoFromJson = gson.fromJson(autoJson, Auto.class);
        System.out.println(autoFromJson);
    }
}
