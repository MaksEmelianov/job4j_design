package ru.job4j.io;

import java.util.Map;
import java.util.HashMap;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("There is no such key");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        isValidate(args);
        for (String arg : args) {
            isValidate(arg);
            String[] split = arg.split("=", 2);
            values.put(split[0].substring(1), split[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        return argsName;
    }

    private static void isValidate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty array args");
        }
    }

    private static void isValidate(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("There is no pointer \"-\"");
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException("There is no separator \"=\"");
        }
        String[] split = arg.substring(1).split("=", 2);
        if (split[0].isBlank()) {
            throw new IllegalArgumentException("Empty key");
        }
        if (split[1].isBlank()) {
            throw new IllegalArgumentException("Empty value");
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
