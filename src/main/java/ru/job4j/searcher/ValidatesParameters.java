package ru.job4j.searcher;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class ValidatesParameters {

    public static void isValidateSaveFile(String saveFile) {
        if (saveFile.isBlank()) {
            throw new IllegalArgumentException("Full file name is empty");
        }
        if (!saveFile.contains(".")) {
            throw new IllegalArgumentException("The file name does not contain a dot for the format");
        }
        int countDot = (int) saveFile.lines()
                .flatMap(s -> Arrays.stream(s.split("\\."))
                        .filter("."::equals)).count();
        if (countDot > 1) {
            throw new IllegalArgumentException("There should be only one dot in the file name.");
        }
        String[] splitName = saveFile.split("\\.");
        if (splitName[0].isBlank()) {
            throw new IllegalArgumentException("File name is empty.");
        }
        if (splitName[1].isBlank()) {
            throw new IllegalArgumentException("The file extension is empty");
        }
    }

    public static void isValidateNameAndType(String typeName, String type) {
        if (typeName.isBlank() || type.isBlank()) {
            throw new IllegalArgumentException("Type name or type is empty");
        }
        if (typeName.endsWith(".")) {
            throw new IllegalArgumentException("Name ends with a dot.");
        }
        if (!"name".equals(type) && !"mask".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("Check type find");
        }
    }

    public static void isValidateArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Check the number of arguments");
        }
        for (var arg : args) {
            if (arg.isBlank()) {
                throw new IllegalArgumentException("One of the arguments is empty");
            }
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("There is no pointer \"-\"");
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException("There is no separator \"=\"");
            }
            String[] splitArg = arg.substring(1).split("=", 2);
            if (splitArg[0].isBlank()) {
                throw new IllegalArgumentException("Check the parameter keys");
            }
            if (splitArg[1].isBlank()) {
                throw new IllegalArgumentException("Check the parameter value");
            }
            String key = splitArg[0];
            if (!key.contains("d") && !key.contains("n")
                    && !key.contains("t") && !key.contains("o")) {
                throw new IllegalArgumentException(String.format(
                        "Check name keys.%n"
                        + "-d - Directory%n"
                        + "-n - Name file | Mask | Regex%n"
                        + "-t - Type find%n"
                        + "-o - File save"));
            }
        }
    }

    public static void isValidateDirValue(Path path) {
        File file = path.toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException("File not exists");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("File not directory");
        }
    }
}
