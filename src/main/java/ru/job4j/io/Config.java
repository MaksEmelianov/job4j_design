package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(s -> !s.startsWith("#") && s.length() > 0)
                    .forEach(s -> values.put(s.split("=")[0], s.split("=")[1]));
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(out::add);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        /*System.out.println(new Config("app.properties"));*/
        Config config = new Config("app.properties");
        config.load();
        for (var entry : config.values.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
