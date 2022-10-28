package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {

    public static void main(String[] args) {
        File file = checkAndGetFile(args);
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("name - %s | size - %s%n", subfile.getName(), subfile.length());
        }
    }

    public static File checkAndGetFile(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalStateException(String.format("Not exist %s", file.getAbsolutePath()));
        }
        if (!file.isDirectory()) {
            throw new IllegalStateException(String.format("Not directory %s", file.getAbsolutePath()));
        }
        return file;
    }
}
