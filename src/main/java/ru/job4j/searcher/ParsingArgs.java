package ru.job4j.searcher;

import java.util.HashMap;
import java.util.Map;

public class ParsingArgs {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("There is no such key");
        }
        return values.get(key);
    }

    public static ParsingArgs of(String[] args) {
        ParsingArgs parsingArgs = new ParsingArgs();
        parsingArgs.parse(args);
        return parsingArgs;
    }

    private void parse(String[] args) {
        ValidatesParameters.isValidateArgs(args);
        for (String arg : args) {
            String[] split = arg.split("=", 2);
            values.put(split[0].substring(1), split[1]);
        }
    }
}
